import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import ui.mainFrame;

import javax.swing.*;

public class main {
    public static void main(String[] args) {
        try {
            BeautyEyeLNFHelper.frameBorderStyle=BeautyEyeLNFHelper.frameBorderStyle.generalNoTranslucencyShadow;
            BeautyEyeLNFHelper.launchBeautyEyeLNF();
            BeautyEyeLNFHelper.translucencyAtFrameInactive = true;
            UIManager.put("RootPane.setupButtonVisible", false);
        }
        catch(Exception e)
        {
            System.out.println("加载炫彩皮肤失败！");
        }

        new mainFrame();
    }
}
