package view;

import model.Movie;
import model.MovieDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class MovieAdd extends JPanel {

    private final int iWidth2;
    private final int iHeight2;
    private Image image = null;
    private JTextField title;
    private JTextField score;
    private JTextField titlesub;
    private JTextField during;
    private JTextField releasetime;
    private JLabel duringLabel;
    private JLabel releaseLabel;
    private JTextField story;


    public MovieAdd() {
        // TODO Auto-generated constructor stub

        String[] langid = {"中文", "英文"}, classid = {"剧情", "科幻"};


        URL url = getClass().getResource("/image/movieadd.png");
        ImageIcon imageIcon = new ImageIcon(url);
        image = imageIcon.getImage();
//	    this.image = image;
        this.iWidth2 = image.getWidth(this) / 2;
        this.iHeight2 = image.getHeight(this) / 2;
        setLayout(null);

        title = new JTextField();
        title.setToolTipText("\u6807\u9898");
        title.setBounds(130, 85, 90, 21);
        add(title);
        title.setColumns(10);

        score = new JTextField();
        score.setBounds(130, 125, 129, 37);
        add(score);
        score.setColumns(10);

        titlesub = new JTextField();
        titlesub.setBounds(60, 268, 333, 66);
        add(titlesub);
        titlesub.setColumns(10);

        JLabel titleLabel = new JLabel("\u7535\u5F71\u540D\u79F0");
        titleLabel.setBounds(130, 65, 54, 15);
        add(titleLabel);

        during = new JTextField();
        during.setBounds(184, 196, 46, 21);
        add(during);
        during.setColumns(10);

        JLabel infoLabel = new JLabel("\u5B50\u6807\u9898");
        infoLabel.setBounds(22, 268, 36, 15);
        add(infoLabel);

        releasetime = new JTextField();
        releasetime.setBounds(130, 220, 54, 21);
        add(releasetime);
        releasetime.setColumns(10);

        JLabel regionLabel = new JLabel("\u4E0A\u6620\u5730\u533A");
        regionLabel.setBounds(270, 223, 54, 15);
        add(regionLabel);

        duringLabel = new JLabel("\u4E0A\u6620\u65F6\u957F");
        duringLabel.setBounds(240, 199, 54, 15);
        add(duringLabel);

        releaseLabel = new JLabel("\u4E0A\u6620\u65F6\u95F4");
        releaseLabel.setBounds(66, 223, 54, 15);
        add(releaseLabel);

        final JComboBox regioncombo = new JComboBox();
        regioncombo.setModel(new DefaultComboBoxModel(new String[]{"\u4E2D\u56FD\u5927\u9646", "\u6E2F\u6FB3\u53F0", "\u5916\u56FD"}));
        regioncombo.setBounds(184, 220, 83, 21);
        add(regioncombo);

        JLabel ratingLabel = new JLabel("\u7535\u5F71\u8BC4\u5206");
        ratingLabel.setBounds(130, 103, 54, 21);
        add(ratingLabel);

        final JComboBox classCombo = new JComboBox();
        classCombo.setModel(new DefaultComboBoxModel(new String[]{"\u5267\u60C5", "\u79D1\u5E7B"}));
        classCombo.setBounds(130, 172, 59, 21);
        add(classCombo);

        JLabel classLabel = new JLabel("\u7C7B\u578B");
        classLabel.setBounds(64, 175, 54, 15);
        add(classLabel);

        final JComboBox langcombo = new JComboBox();
        langcombo.setModel(new DefaultComboBoxModel(new String[]{"\u4E2D\u6587", "\u82F1\u6587"}));
        langcombo.setBounds(130, 196, 54, 21);
        add(langcombo);

        JLabel LangLabel = new JLabel("\u8BED\u8A00");
        LangLabel.setBounds(64, 199, 36, 15);
        add(LangLabel);

        JButton btnadd = new JButton("\u6DFB\u52A0");
        btnadd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if (e.getActionCommand().equals("添加")) {
                    Movie movie = new Movie(title.getText(), score.getText(), titlesub.getText(), story.getText(), String.valueOf(classCombo.getSelectedIndex()), releasetime.getText(), during.getText(), String.valueOf(regioncombo.getSelectedIndex()), String.valueOf(langcombo.getSelectedIndex()));


                    if (new MovieDAO().add(movie)) {
                        Menu.setStatus("添加行成功");
                    } else {
                        Menu.setStatus("添加行失败");
                    }
                    MovieAdminTab.movietable.tablereload();
                }
            }
        });
        btnadd.setBounds(130, 415, 90, 23);
        add(btnadd);

        JButton btnclose = new JButton("\u5173\u95ED");
        btnclose.setBounds(232, 415, 92, 23);
        add(btnclose);

        story = new JTextField();
        story.setBounds(60, 342, 333, 63);
        add(story);
        story.setColumns(10);

        JLabel storyLabel = new JLabel("\u5267\u60C5\u4ECB\u7ECD");
        storyLabel.setBounds(4, 342, 54, 15);
        add(storyLabel);

//		class MovieAddAction implements ActionListener{
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				if(e.getActionCommand().equals("保存")) {
//					new Movie( title.getText(), score.getText(), titlesub.getText(), story.getText(), String.valueOf(classCombo.getSelectedIndex()), releasetime.getText(), during.getText(), String.valueOf(regioncombo.getSelectedIndex()), String.valueOf(langcombo.getSelectedIndex()));
//					
//				}else if (e.getActionCommand().equals("关闭")) {
//					
//				}
//				
//			}
//			
//		}

    }


    public MovieAdd(Image image) {
        this.image = image;
        this.iWidth2 = image.getWidth(this) / 2;
        this.iHeight2 = image.getHeight(this) / 2;
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            int x = this.getParent().getWidth() / 2 - iWidth2;
            int y = this.getParent().getHeight() / 2 - iHeight2;
            g.drawImage(image, x, y, this);
        }
    }
}


