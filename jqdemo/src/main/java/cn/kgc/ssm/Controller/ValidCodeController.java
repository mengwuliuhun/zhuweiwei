package cn.kgc.ssm.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Random;

/**
 * @author 杨卫兵
 * @version V1.00
 * @date 2019-09-03 08:46
 * @since V1.00
 */
@Controller
public class ValidCodeController {

    @RequestMapping("/codes")
    public void validcode(OutputStream out, HttpSession session) throws Exception {
        Random rnd = new Random();
        String code = "";
        for (int i = 0; i < 4; i++) {
            int n = rnd.nextInt(62);//0-61
            if (n < 10) {
                code += n;
            } else if (n < 36) {//Upper
                code += (char) (n - 10 + 'A');
            } else {
                code += (char) (n - 36 + 'a');
            }
        }

        session.setAttribute("code", code);

        BufferedImage image = new BufferedImage(
                100, 25, BufferedImage.TYPE_INT_RGB);
        Graphics gra = image.getGraphics();//画笔
        gra.setColor(Color.yellow); // 设置背景色
        gra.fillRect(2, 2, 96, 21); // 绘制边框
        gra.setFont(new Font("Times New Roman", Font.BOLD, 20)); // 设置字体
        for (int i = 0; i < 4; i++) {
            Color clr = new Color(rnd.nextInt(256),
                    rnd.nextInt(256), rnd.nextInt(256));
            gra.setColor(clr); //设置字体颜色
            gra.drawString(code.substring(i, i + 1), 15 + 20 * i, 18); //绘制文字
        }

        ImageIO.write(image, "jpeg", out);
        out.close();

    }
}
