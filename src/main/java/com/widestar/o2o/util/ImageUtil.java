package com.widestar.o2o.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ImageUtil {
  private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
  private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
  private static final Random r = new Random();
  private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

  public static String generateThumbnail(File thumbnail, String targetAddr) {
    String realFileName = getRandomFileName();
    String extension = getFileExtension(thumbnail);
    makeDirPath(targetAddr);
    String relativeAddr = targetAddr + realFileName + extension;
    logger.debug("current relativeAddr is:" + relativeAddr);
    File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
    logger.debug("current complete addr is:" + PathUtil.getImgBasePath() + relativeAddr);
    try {
      /*Thumbnails.of(thumbnail).size(200, 200)
          .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/long.png")), 0.25f)
          .outputQuality(0.8f).toFile(dest);*/
      //水印图
      File watermarkPic = new File(basePath + "/long.png");
      //缩放水印图
      BufferedImage water = Thumbnails.of(ImageIO.read(watermarkPic))
          .scale(0.25)
          .asBufferedImage();
      //给原始图加水印
      Thumbnails.of(thumbnail)
          //.size(800, 800)
          //.rotate(90)
          .scale(0.8)
          .watermark(Positions.CENTER, water, 0.5f)
          /*.watermark(Positions.BOTTOM_RIGHT, water, 0.5f)//0.25f是水印图的透明度
          .watermark(Positions.BOTTOM_LEFT, water, 0.75f)
          .watermark(Positions.TOP_LEFT, water, 0.2f)
          .watermark(Positions.TOP_RIGHT, water, 0.4f)*/
          .outputQuality(1f).toFile(dest);//1f是目标图相对原始图的压缩比
    } catch (IOException e) {
      e.printStackTrace();
    }
    return relativeAddr;
  }

  /**
   * 创建目标路径所涉及到的目录，即/home/work/zeguang/xxx.jpg,
   * 那么home work zeguang 这三个文件夹都得自动创建
   *
   * @param targetAddr
   */
  private static void makeDirPath(String targetAddr) {
    String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
    File dirPath = new File(realFileParentPath);
    if (!dirPath.exists()) {
      dirPath.mkdirs();
    }
  }

  /**
   * 获取输入文件流的扩展名
   *
   * @param cFile
   * @return
   */
  private static String getFileExtension(File cFile) {
    String originalFileName = cFile.getName();
    return originalFileName.substring(originalFileName.lastIndexOf("."));
  }

  /**
   * 生成随机文件名，当前年月日小时分钟秒钟+5位随机数
   *
   * @return
   */
  public static String getRandomFileName() {
    //获取随机的5位数
    int rannum = r.nextInt(89999) + 10000;
    String nowTimeStr = sDateFormat.format(new Date());
    return nowTimeStr + rannum;
  }

  public static void main(String[] args) throws IOException {
    //原始图
    File originPic = new File("/Users/didi/Pictures/jing.jpg");
    //水印图
    File watermarkPic = new File(basePath + "/mao.jpeg");
    //缩放水印图
    BufferedImage water = Thumbnails.of(ImageIO.read(watermarkPic))
        .scale(0.15)
        .asBufferedImage();
    //给原始图加水印
    Thumbnails.of(originPic)
        //.size(800, 800)
        //.rotate(90)
        .scale(0.8)
        .watermark(Positions.BOTTOM_RIGHT, water, 0.5f)//0.25f是水印图的透明度
        .watermark(Positions.BOTTOM_LEFT, water, 0.75f)
        .watermark(Positions.TOP_LEFT, water, 0.2f)
        .watermark(Positions.TOP_RIGHT, water, 0.4f)
        .outputQuality(1f).toFile("/Users/didi/Pictures/jingNew.jpg");//1f是目标图相对原始图的压缩比
  }
}
