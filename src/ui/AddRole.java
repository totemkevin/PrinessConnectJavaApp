package ui;

import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import entity.RoleEntity;
import service.RoleService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class AddRole extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	protected File selectedFile = null;

	/**
	 * Create the frame.
	 */
	public AddRole() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 509, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(14, 13, 247, 229);
		contentPane.add(lblNewLabel);

		// 角色名稱
		JLabel label = new JLabel("\u89D2\u8272\u540D\u7A31");
		label.setBounds(302, 13, 99, 19);
		contentPane.add(label);

		// 角色名稱 textField
		textField = new JTextField();
		textField.setBounds(302, 45, 116, 25);
		contentPane.add(textField);
		textField.setColumns(10);

		// 上傳檔案
		JLabel label_1 = new JLabel("\u4E0A\u50B3\u6A94\u6848");
		label_1.setBounds(302, 83, 116, 19);
		contentPane.add(label_1);

		// 檔案資訊
		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setBounds(302, 115, 175, 87);
		contentPane.add(lblNewLabel_1);

		JButton btnOpen = new JButton("Open...");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File("resource/img"));
				fileChooser.setAcceptAllFileFilterUsed(false);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("png", "png");
				fileChooser.addChoosableFileFilter(filter);
				int result = fileChooser.showOpenDialog(contentPane);
				if (result == JFileChooser.APPROVE_OPTION) {
					selectedFile = fileChooser.getSelectedFile();
					BufferedImage img = null;
					try {
						img = ImageIO.read(selectedFile);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Image newImage = img.getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(),
							Image.SCALE_SMOOTH);
					Icon icon = new ImageIcon(newImage);
					lblNewLabel.setIcon(icon);
					
					StringBuilder sb = new StringBuilder();
					sb.append("<html>");
					sb.append("<p>檔名 : ");
					sb.append(selectedFile.getName());
					sb.append("</p>");
					sb.append("<p>檔案路徑 : ");
					sb.append(selectedFile.getAbsolutePath());
					sb.append("</p>");
					sb.append("</html>");
					String info = sb.toString();
					lblNewLabel_1.setText(info );
				}
			}
		});
		btnOpen.setBounds(378, 79, 99, 27);
		contentPane.add(btnOpen);
		
		//上傳
		JButton button = new JButton("\u4E0A\u50B3");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectedFile != null) {
					RoleEntity roleEntity = new RoleEntity();
					roleEntity.setImg(selectedFile.getName());
					roleEntity.setName(textField.getText());

					RoleService roleService = new RoleService();
					try {
						roleService.createRole(roleEntity);
						JOptionPane.showMessageDialog(null, "上傳成功", "上傳成功", JOptionPane.INFORMATION_MESSAGE);
					}catch(Exception exc) {
						JOptionPane.showMessageDialog(null, "上傳失敗", "上傳失敗"+e.toString(), JOptionPane.INFORMATION_MESSAGE);
					}
					
					
				}else {
					JOptionPane.showMessageDialog(null, "未選擇檔案", "未選擇檔案", JOptionPane.OK_OPTION);
				}
			}
		});
		button.setBounds(302, 215, 99, 27);
		contentPane.add(button);

	}
}
