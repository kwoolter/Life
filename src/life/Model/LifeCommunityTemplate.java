/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package life.Model;

import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;

/**
 *
 * @author KeithW
 */
public class LifeCommunityTemplate {

    public LifeCommunity load(String sFileName) {

        BufferedImage imgSource = null;
        LifeCommunity community = null;

        try {
            imgSource = ImageIO.read(new File("src/life/resources/" + sFileName));

            int iHeight = imgSource.getHeight();
            int iWidth = imgSource.getWidth();

            int iMinHeight = 150;
            int iMinWidth = 150;

            community = new LifeCommunity(Math.max(iMinWidth, iWidth), Math.max(iMinHeight, iHeight));

            for (int x = 0; x < community.getWidth(); x++) {
                for (int y = 0; y < community.getHeight(); y++) {

                    if (x < iWidth && y < iHeight) {

                        int iColour = imgSource.getRGB(x, y);

                        // System.out.println("x:y:rgb=" + x+":"+y+":"+iColour);
                        if (iColour < 0xffcccccc) {
                            LifeCell cell = new LifeCell(LifeCell.LifeState.ALIVE);
                            community.addCell(cell, x, y);

                        } else {
                            LifeCell cell = new LifeCell(LifeCell.LifeState.DEAD);
                            community.addCell(cell, x, y);
                        }
                    } else {
                        LifeCell cell = new LifeCell(LifeCell.LifeState.DEAD);
                        community.addCell(cell, x, y);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return community;

    }

}
