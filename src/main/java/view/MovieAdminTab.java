package view;

import javax.swing.*;
import java.awt.*;

public class MovieAdminTab extends JPanel {
	public static JTabbedPane tabbedPane;
	protected static MovieTable movietable;

	public MovieAdminTab() {
		setLayout(new BorderLayout(0, 0));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		tabbedPane.addTab("表格模式", movietable = new MovieTable());
		tabbedPane.addTab("添加新记录", new MovieAdd());


		add(tabbedPane);


	}

}
