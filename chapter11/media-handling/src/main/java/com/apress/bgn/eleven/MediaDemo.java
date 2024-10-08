/*
Freeware License, some rights reserved

Copyright (c) 2024 Iuliana Cosmina

Permission is hereby granted, free of charge, to anyone obtaining a copy 
of this software and associated documentation files (the "Software"), 
to work with the Software within the limits of freeware distribution and fair use. 
This includes the rights to use, copy, and modify the Software for personal use. 
Users are also allowed and encouraged to submit corrections and modifications 
to the Software for the benefit of other users.

It is not allowed to reuse,  modify, or redistribute the Software for 
commercial use in any way, or for a user's educational materials such as books 
or blog articles without prior permission from the copyright holder. 

The above copyright notice and this permission notice need to be included 
in all copies or substantial portions of the software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS OR APRESS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package com.apress.bgn.eleven;

import org.apache.commons.imaging.formats.jpeg.exif.ExifRewriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.MultiResolutionImage;
import java.io.*;

/**
 * @author Iuliana Cosmina
 * since 1.0
 */
public class MediaDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(MediaDemo.class);
    void main() {
        var src = new File("chapter11/media-handling/src/main/resources/input/the-beach.jpg");

        try {
            BufferedImage originalImage = ImageIO.read(src);
            LOGGER.debug(" --- Original image sizes {} x {} ---", originalImage.getWidth(), originalImage.getHeight() );

            LOGGER.info(" --- Removing EXIF info ---");
            File destNoExif = new File("chapter11/media-handling/src/main/resources/output/the-beach-no-exif.jpg");
            removeExifMetadata(src, destNoExif);

            LOGGER.info(" --- Creating 25% image ---");
            File dest25 = new File("chapter11/media-handling/src/main/resources/output/the-beach_25.jpg");
            resize(dest25, src, 0.25f);
            BufferedImage dest25Image = ImageIO.read(dest25);
            LOGGER.debug(" --- 25% image sizes {} x {} ---", dest25Image.getWidth(), dest25Image.getHeight() );

            LOGGER.info(" --- Creating 50% image ---");
            File dest50 = new File("chapter11/media-handling/src/main/resources/output/the-beach_50.jpg");
            resize(dest50, src, 0.5f);
            BufferedImage dest50Image = ImageIO.read(dest50);
            LOGGER.debug(" --- 50% image sizes {} x {} ---", dest50Image.getWidth(), dest50Image.getHeight() );

            LOGGER.info(" --- Creating 75% image ---");
            File dest75 = new File("chapter11/media-handling/src/main/resources/output/the-beach_75.jpg");
            resize(dest75, src, 0.75f);
            BufferedImage dest75Image = ImageIO.read(dest75);
            LOGGER.debug(" --- 75% image sizes {} x {} ---", dest75Image.getWidth(), dest75Image.getHeight() );

            Image[] imgList = new Image[]{
                    ImageIO.read(src), // 2000 x 972
                    ImageIO.read(dest25), // 500 x 243
                    ImageIO.read(dest50), // 1000 x 486
                    ImageIO.read(dest75) // 1500 x 729
            };

            LOGGER.info(" --- Creating multi-resolution image ---");
            File destVariant = new File("chapter11/media-handling/src/main/resources/output/the-beach-variant.jpg");
            createMultiResImage(destVariant, imgList);

            BufferedImage variantImg = ImageIO.read(destVariant);
            LOGGER.info("variant width x height :  {} x {}", variantImg.getWidth(), variantImg.getHeight());
            BufferedImage dest25Img = ImageIO.read(dest25);
            LOGGER.info("dest25Img width x height :  {} x {}", dest25Img.getWidth(), dest25Img.getHeight());
            LOGGER.info("Are identical? {}", variantImg.equals(dest25Img));
        } catch (Exception e) {
            LOGGER.error("Something bad happened.", e);
        }
    }

    public void removeExifMetadata(final File jpegImageFile, final File dst) throws IndexOutOfBoundsException, IOException {
        try (FileOutputStream fos = new FileOutputStream(dst);
             OutputStream os = new BufferedOutputStream(fos)) {
            new ExifRewriter().removeExifMetadata(jpegImageFile, os);
        }
    }

    // less precise
    private static void resizeSimple(final File dest, final File src, final float percent) throws IOException {
        BufferedImage originalImage = ImageIO.read(src);
        int scaledWidth = (int) (originalImage.getWidth() * percent);
        int scaledHeight = (int) (originalImage.getHeight() * percent);
        Image resultingImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
        BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        ImageIO.write(outputImage, "jpg", dest);
    }

    private static void resize(final File dest, final File src, final float percent) throws IOException {
        BufferedImage originalImage = ImageIO.read(src);
        int scaledWidth = (int) (originalImage.getWidth() * percent);
        int scaledHeight = (int) (originalImage.getHeight() * percent);

        BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, originalImage.getType());

        Graphics2D g2d = outputImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();
        outputImage.flush();

        ImageIO.write(outputImage, "jpg", dest);
    }

    private static void createMultiResImage(final File dest, final Image[] imgList) throws IOException {
        MultiResolutionImage mrImage = //new BaseMultiResolutionImage(0,imgList);
                new SmartMultiResolutionImage(0, imgList);

        var variants = mrImage.getResolutionVariants();

        variants.forEach(i -> LOGGER.info(i.toString()));


        Image img = mrImage.getResolutionVariant(500, 200);
        LOGGER.info("Most fit to the requested size<{},{}>: <{},{}>", 500, 200, img.getWidth(null), img.getHeight(null));

       /* img = mrImage.getResolutionVariant(500, 300);
        log.info("Most fit to the requested size<{},{}>: <{},{}>", 500, 300, img.getWidth(null), img.getHeight(null));

        img = mrImage.getResolutionVariant(400, 300);
        log.info("Most fit to the requested size<{},{}>: <{},{}>", 400, 300, img.getWidth(null), img.getHeight(null));

        img = mrImage.getResolutionVariant(600, 300);
        log.info("Most fit to the requested size<{},{}>: <{},{}>", 600, 300, img.getWidth(null), img.getHeight(null));
*/
        if (img instanceof BufferedImage) {
            ImageIO.write((BufferedImage) img, "jpg", dest);
        }
    }
}
