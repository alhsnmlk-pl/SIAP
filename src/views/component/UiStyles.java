/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views.component;

import com.formdev.flatlaf.ui.FlatComboBoxUI;
import com.formdev.flatlaf.ui.FlatLineBorder;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Component;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;


/**
 *
 * @author Al
 */
public final class UiStyles {

    private UiStyles() {
    }

    public static void seRoundPanel(JPanel panel, 
                                    int top, int left, int bottom, int right,
                                    int radius, 
                                    String borderColor, 
                                    float borderWidth) {
        panel.setBorder(new FlatLineBorder(
                        new Insets(top, left, bottom, right), 
                        Color.decode(borderColor), 
                        borderWidth, 
                        radius));
    }

    public static void setTextField(JTextField field, 
                                    String placeholder, 
                                    String background, 
                                    String foreground,
                                    String caretColor, 
                                    String borderColor, 
                                    String focusedBorderColor, 
                                    float borderWidth,
                                    float innerFocuswidth) {
        field.putClientProperty("JTextField.placeholderText", placeholder);
        field.putClientProperty("FlatLaf.style", "arc:999; "
                + "background:" + background 
                + "; foreground:" + foreground
                + "; caretColor:" + caretColor 
                + "; borderWidth:" + borderWidth 
                + "; borderColor:" + borderColor
                + "; focusedBorderColor:" + focusedBorderColor 
                + "; focusWidth:0; "
                + "innerFocusWidth:" + innerFocuswidth +";");
    }

    public static void setTextFieldv2(JTextField field, String placeholder) {
        setTextField(field, placeholder, "#F6F7F5", "#66706B", "#66706B",
                "#ECEDEB", "#ECEDEB", 0f, 0f);
    }

    public static void setButton(JButton button,
            String background,
            String foreground,
            String hoverBackground,
            String pressedBackground,
            String borderColor,
            int borderWidth) {
        String borderStyle = borderWidth == 0
                ? "borderWidth:0;" : "borderWidth:" + borderWidth
                + "; borderColor:" + borderColor
                + "; hoverBorderColor:" + borderColor
                + "; pressedBorderColor:" + borderColor + ";";

        button.putClientProperty("FlatLaf.style", "arc:100;" + borderStyle 
                + "focusWidth:0; innerFocusWidth:0;"
                + "background:" + background 
                + "; foreground:" + foreground
                + "; hoverBackground:" + hoverBackground 
                + "; pressedBackground:" + pressedBackground + ";");
        button.setFocusPainted(false);
    }
    
    public static void setButtonV2(JButton btn){
        setButton(btn, "#FFFFFF", "#0D3B26", "#EEEEEE", "#E6E6E6", "#144D32", 1);
    } 
    
    public static void setButtonV3(JButton btn){
        setButton(btn, "#FFFFFF", "#E7000B", "#EEEEEE", "#E6E6E6", "#E7000B", 1);
    }

    public static void setTogleBtn(JToggleButton button, 
            String background,
            String foreground,
            String hoverBackground, 
            String pressedBackground,
            String pressedForeground,
            String selectedBackground, 
            String selectedForeground) {
        button.putClientProperty("FlatLaf.style", "arc:100; borderWidth:0; focusWidth:0; innerFocusWidth:0;"
                + "background:" + background 
                + "; foreground:" + foreground
                + "; hoverBackground:" + hoverBackground 
                + "; pressedBackground:" + pressedBackground
                + "; pressedForeground:" + pressedForeground
                + "; selectedBackground:" + selectedBackground 
                + "; selectedForeground:" + selectedForeground);
        button.setFocusPainted(false);
    }
    
    public static void setCBox(JComboBox<?> cbox) {

        cbox.putClientProperty("JComponent.minimumHeight", 40);

        cbox.putClientProperty("FlatLaf.style",
                "arc:999;"
                + "focusWidth:0; innerFocusWidth:0;"
                + "padding:0,16,0,0;"
                + "background:#FFFFFF;"
                + "foreground:#16201B;"
                + "borderColor:#337253;"
                + "focusedBorderColor:#337253;"
                + "buttonStyle:none;"
                + "arrowType:chevron;"
                + "buttonArrowColor:#16201B;"
                + "popupBackground:#FFFFFF;");

        // FlatLaf sizes this slot from font height; widen it around the chevron.
        cbox.setUI(new FlatComboBoxUI() {
            @Override
            protected LayoutManager createLayoutManager() {
                LayoutManager flatLayout = super.createLayoutManager();
                return new LayoutManager() {
                    @Override
                    public void addLayoutComponent(String name, Component component) {
                        flatLayout.addLayoutComponent(name, component);
                    }

                    @Override
                    public void removeLayoutComponent(Component component) {
                        flatLayout.removeLayoutComponent(component);
                    }

                    @Override
                    public Dimension preferredLayoutSize(Container parent) {
                        return flatLayout.preferredLayoutSize(parent);
                    }

                    @Override
                    public Dimension minimumLayoutSize(Container parent) {
                        return flatLayout.minimumLayoutSize(parent);
                    }

                    @Override
                    public void layoutContainer(Container parent) {
                        flatLayout.layoutContainer(parent);
                        if (arrowButton == null) {
                            return;
                        }

                        Insets insets = comboBox.getInsets();
                        int width = UIScale.scale(42); // 16px + 10px chevron + 16px
                        int x = comboBox.getComponentOrientation().isLeftToRight()
                                ? comboBox.getWidth() - insets.right - width
                                : insets.left;
                        arrowButton.setBounds(x, arrowButton.getY(), width, arrowButton.getHeight());
                    }
                };
            }
        });
    }
}
