package com.fly.springbootdemo.utils;

import com.fly.springbootdemo.common.enums.StatusCode;
import com.fly.springbootdemo.common.enums.TimePatternEnum;
import com.fly.springbootdemo.exception.CaseServerException;
import kong.unirest.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * 文件操作方法
 */
@Slf4j
public class FileUtil {
    public FileUtil() {
    }

    /**
     * 解压文件
     *
     * @param zipPath   要解压的目标文件
     * @param targetDir 指定解压目录
     * @return 解压结果：成功，失败
     */
    public static boolean decompressZip(String zipPath, String targetDir) {
        File zipFile = new File(zipPath);
        boolean flag = false;

        File pathFile = new File(targetDir);
        if (!pathFile.exists()) {
            log.info("{} 目录不存在, 自动创建", targetDir);
            pathFile.mkdir();
        }

        try {
            ZipFile zip = new ZipFile(zipFile, Charset.forName("utf-8"));
            for (Enumeration entries = zip.entries(); entries.hasMoreElements(); ) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                String zipEntryName = entry.getName();
                InputStream in = zip.getInputStream(entry);

                //指定解压后的文件夹+当前zip文件的名称
                String outPath = (targetDir + zipEntryName).replace("/", File.separator);

                // 判断路径是否存在，不存在则创建文件路径，同时添加检验
                String canonicalDescDirPath = pathFile.getCanonicalPath();
                File file = new File(outPath.substring(0, outPath.lastIndexOf(File.separator)));
                String canonicalDescFile = file.getCanonicalPath() + File.separator;
                if (!canonicalDescFile.startsWith(canonicalDescDirPath + File.separator)) {
                    throw new ArithmeticException("Entry is outside of the target dir: " + zipEntryName);
                }

                if (!file.exists()) {
                    file.mkdirs();
                }
                //判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
                if (new File(outPath).isDirectory()) {
                    continue;
                }
                //保存文件路径信息（可利用md5.zip名称的唯一性，来判断是否已经解压）
                System.err.println("当前zip解压之后的路径为：" + outPath);
                OutputStream out = new FileOutputStream(outPath);
                byte[] buf1 = new byte[2048];
                int len;
                while ((len = in.read(buf1)) > 0) {
                    out.write(buf1, 0, len);
                }
                in.close();
                out.close();
            }

            flag = true;
            //必须关闭，要不然这个zip文件一直被占用着，要删删不掉，改名也不可以，移动也不行，整多了，系统还崩了。
            zip.close();

        } catch (IOException e) {
            throw new CaseServerException("解压文件失败：" + e.getMessage(), StatusCode.FILE_IMPORT_ERROR);

        }

        return flag;
    }

    /**
     * 压缩文件
     *
     * @param sourcePath 要压缩的文件夹
     * @param destPath   压缩文件
     * @return 压缩文件
     */
    public static void compressZip(String sourcePath, String destPath) {
        File resourcesFile = new File(sourcePath);
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(destPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(outputStream));
        try {
            createCompressedFile(out, resourcesFile, "");
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new CaseServerException("压缩文件失败：" + e.getMessage(), StatusCode.FILE_EXPORT_ERROR);
        }
    }

    public static void createCompressedFile(ZipOutputStream out, File file, String dir) throws Exception {
        if (file.isDirectory()) {
            //得到文件列表信息
            File[] files = file.listFiles();
            //将文件夹添加到下一级打包目录
            out.putNextEntry(new ZipEntry(dir + "/"));
            dir = dir.length() == 0 ? "" : dir + "/";
            //循环将文件夹中的文件打包
            for (int i = 0; i < files.length; i++) {
                createCompressedFile(out, files[i], dir + files[i].getName());
            }
        } else {
            //文件输入流
            FileInputStream fis = new FileInputStream(file);
            out.putNextEntry(new ZipEntry(dir));
            //进行写操作
            int j = 0;
            byte[] buffer = new byte[1024];
            while ((j = fis.read(buffer)) > 0) {
                out.write(buffer, 0, j);
            }
            //关闭输入流
            fis.close();
        }
    }

    /**
     * 文件上传
     * @param path：文件所在路径
     * @param file：
     * @return
     */
    public static String fileUpload(String path, MultipartFile file) {
        String format = TimeUtil.getDate2(TimePatternEnum.Y_M_M_PATTERN.getPattern());

        // 文件夹的名字
        File folder = new File(path + format);

        // 如果文件夹为空，则新建文件夹
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }
        // 对上传的文件重命名，避免文件重名
        // 获取文件的名字
        String oldName = StringUtils.hasLength(file.getOriginalFilename()) ? file.getName() : file.getOriginalFilename();
        // 生成新的随机的文件名字
        String newName = UUID.randomUUID().toString()
                + oldName.substring(oldName.lastIndexOf("."), oldName.length());
        try {
            file.transferTo(new File(folder, newName));

            return format + newName;
        } catch (IOException e) {
            throw new CaseServerException("图片上传失败：" + e.getMessage(), StatusCode.FILE_IMPORT_ERROR);
        }
    }

    /**
     * File 转为 MultipartFile
     * @param file
     * @return
     */
    private static MultipartFile getMultipartFile(File file) {
        FileInputStream fileInputStream = null;
        MultipartFile multipartFile = null;
        try {
            fileInputStream = new FileInputStream(file);
            multipartFile = new MockMultipartFile(file.getName(), file.getName(),
                    ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return multipartFile;
    }
}
