package top.chen.train.business.config;

import com.google.code.kaptcha.BackgroundProducer;
import com.google.code.kaptcha.util.Configurable;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * @author ChenQi
 * @date 2023/12/1
 * @description: KaptchaNoBackground
 */
public class KaptchaNoBackground extends Configurable implements BackgroundProducer {

    public KaptchaNoBackground() {
    }

    @Override
    public BufferedImage addBackground(BufferedImage baseImage) {
        int width = baseImage.getWidth();
        int height = baseImage.getHeight();
        BufferedImage imageWithBackground = new BufferedImage(width, height, 1);
        Graphics2D graph = (Graphics2D) imageWithBackground.getGraphics();
        graph.fill(new Rectangle2D.Double(0.0D, 0.0D, (double) width, (double) height));
        graph.drawImage(baseImage, 0, 0, null);
        return imageWithBackground;
    }
}
