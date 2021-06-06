package controller;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.TimerTask;
import java.awt.Font;
import javax.swing.JButton;

public class MainFrame extends JFrame {
	private JPanel contentPane;
	private JLabel lblThamCo;
	private ArrayList<Point> listToaDo = new ArrayList<>();
	private ArrayList<JLabel> listNot = new ArrayList<>();
	private JLabel dauRan;
	private Point chiDuong = new Point(100, 100);
	private Point chiDuongTam = new Point();
	private int huongDi = 0; // xuong-0, len-1, trai-2, phai-3
	private Point toaDoThucAn;
	private boolean isThucAn = false;
	private JLabel hienThiThucAn = new JLabel();
	private JLabel lblDiemSo;
	private static int diem = 0;
	private JLabel lblDiemTop;
	private static int diemHang;
	private JLabel lblTenTop;
	private static String tenHang;
	private JPanel pannel_giaoDienKT;
	private JButton btnPlay;
	private JButton btnExit;

	public MainFrame() {
		this.setContentPane();
		hienThiBackground();
		run();
	}

	// hien thi khi ket thuc game
	private void hienThiGiaoDienKetThuc() {

		contentPane.removeAll();
		contentPane.setVisible(false);

		JLabel lblNewLabel_1 = new JLabel("S    N    A    K    E");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 45));
		lblNewLabel_1.setBounds(234, 0, 385, 105);

		JLabel lblNewLabel = new JLabel("G A M E  O V E R");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 80));
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		lblNewLabel.setBounds(110, 150, 700, 105);

		JLabel lblDiemTop = new JLabel("");
		lblDiemTop.setText("NEW SCORES: " + String.valueOf(diem));
		lblDiemTop.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDiemTop.setForeground(Color.YELLOW);
		lblDiemTop.setBounds(200, 299, 178, 41);

		JLabel lblNewLabel_2_1 = new JLabel("PLAYER NAME: ");
		lblNewLabel_2_1.setForeground(Color.YELLOW);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(200, 350, 178, 41);

		JTextField userName = new JTextField();
		userName.setBounds(340, 360, 300, 25);
		userName.setFont(new Font("Nirmala UI", Font.PLAIN, 18));
		userName.setForeground(Color.black);

		JButton btnNhapTen = new JButton("OK");
		btnNhapTen.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNhapTen.setBackground(Color.YELLOW);
		btnNhapTen.setBounds(650, 360, 60, 25);
		btnNhapTen.setVisible(true);

		btnPlay = new JButton("PLAY");
		btnPlay.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnPlay.setBackground(Color.GREEN);
		btnPlay.setBounds(339, 432, 132, 67);
		btnPlay.setVisible(true);

		btnExit = new JButton("EXIT");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnExit.setBackground(Color.RED);
		btnExit.setBounds(339, 543, 132, 67);
		btnExit.setVisible(true);

		pannel_giaoDienKT = new JPanel();
		pannel_giaoDienKT.setBackground(Color.black);
		pannel_giaoDienKT.setBounds(6, 0, 830, 654);
		pannel_giaoDienKT.setLayout(null);
		setContentPane(pannel_giaoDienKT);

		pannel_giaoDienKT.add(lblNewLabel_1);
		pannel_giaoDienKT.add(btnPlay);
		pannel_giaoDienKT.add(btnExit);
		pannel_giaoDienKT.add(lblNewLabel);
		if (diem > diemHang) {
			pannel_giaoDienKT.add(lblNewLabel_2_1);
			pannel_giaoDienKT.add(userName);
			pannel_giaoDienKT.add(lblDiemTop);
			pannel_giaoDienKT.add(btnNhapTen);
		}
		btnPlay.addMouseListener(new MouseAdapter() {
			// click
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		btnExit.addMouseListener(new MouseAdapter() {
			// click
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		btnNhapTen.addMouseListener(new MouseAdapter() {
			// click
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					ghiFile(userName.getText(), diem);
					userName.setText("");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				;
			}
		});
	}

	private void setContentPane() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 857, 696);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("SNAKE");
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

	private void hienThiBackground() {
		docFile();

		// grass
		lblThamCo = new JLabel("");
		lblThamCo.setBackground(Color.ORANGE);
		lblThamCo.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/thamCo.jpg")));
		lblThamCo.setBounds(30, 30, 580, 600);
		contentPane.add(lblThamCo);
		// wall
		JLabel lblBucTuongTop = new JLabel("");
		lblBucTuongTop.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/wallLie.png")));
		lblBucTuongTop.setBounds(0, 0, 612, 30);
		contentPane.add(lblBucTuongTop);
		JLabel lblBucTuongBottom = new JLabel("");
		lblBucTuongBottom.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/wallLie.png")));
		lblBucTuongBottom.setBounds(0, 630, 612, 30);
		contentPane.add(lblBucTuongBottom);
		JLabel lblBucTuongRight = new JLabel("1000");
		lblBucTuongRight.setIcon(new ImageIcon("D:\\LAPTRINHHUONGDOITUONG\\1111.png"));
		lblBucTuongRight.setBounds(610, 0, 30, 660);
		contentPane.add(lblBucTuongRight);
		JLabel lblBucTuongLeft = new JLabel("");
		lblBucTuongLeft.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/wallStand.png")));
		lblBucTuongLeft.setBounds(0, 0, 30, 640);
		contentPane.add(lblBucTuongLeft);
		// snake
		JPanel panelTittle = new JPanel();
		panelTittle.setBackground(Color.GREEN);
		panelTittle.setBounds(661, 16, 163, 63);
		contentPane.add(panelTittle);
		panelTittle.setLayout(null);
		JLabel lblTitle = new JLabel("SNAKE");
		lblTitle.setBounds(3, 10, 163, 50);
		panelTittle.add(lblTitle);
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setFont(new Font("Segoe Script", Font.BOLD, 40));
		lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
		// thong tin
		JPanel panelMenuThongTin = new JPanel();
		contentPane.add(panelMenuThongTin);
		panelMenuThongTin.setBackground(Color.BLACK);
		panelMenuThongTin.setBounds(645, 101, 192, 548);
		panelMenuThongTin.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		panel.setBounds(0, 123, 195, 5);
		panelMenuThongTin.add(panel);
		panel.setLayout(null);
		// top 1

		JLabel lblKing = new JLabel("");
		lblKing.setHorizontalAlignment(SwingConstants.TRAILING);
		lblKing.setForeground(Color.YELLOW);
		lblKing.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/kingdom.png")));
		lblKing.setBounds(58, 0, 63, 64);
		panelMenuThongTin.add(lblKing);

		// ==> diem
		lblDiemTop = new JLabel("0");
		lblDiemTop.setText(String.valueOf(diemHang));
		lblDiemTop.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDiemTop.setHorizontalAlignment(SwingConstants.CENTER);
		lblDiemTop.setForeground(Color.WHITE);
		lblDiemTop.setBounds(0, 61, 184, 18);
		panelMenuThongTin.add(lblDiemTop);

		// diem
		JLabel lblDiemTittle = new JLabel("Scores: ");
		lblDiemTittle.setForeground(Color.ORANGE);
		lblDiemTittle.setBackground(Color.WHITE);
		lblDiemTittle.setHorizontalAlignment(SwingConstants.CENTER);
		lblDiemTittle.setFont(new Font("Nueva Std", Font.PLAIN, 30));
		lblDiemTittle.setBounds(43, 150, 100, 49);
		panelMenuThongTin.add(lblDiemTittle);
		lblDiemSo = new JLabel("0");
		lblDiemSo.setForeground(SystemColor.textHighlightText);
		lblDiemSo.setHorizontalAlignment(SwingConstants.CENTER);
		lblDiemSo.setFont(new Font("Nueva Std", Font.PLAIN, 60));
		lblDiemSo.setBounds(0, 195, 184, 70);
		panelMenuThongTin.add(lblDiemSo);
		// ==> ten
		lblTenTop = new JLabel("");
		lblTenTop.setText(tenHang);
		lblTenTop.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenTop.setForeground(Color.YELLOW);
		lblTenTop.setFont(new Font("Nirmala UI", Font.BOLD, 20));
		lblTenTop.setBounds(0, 80, 184, 33);
		panelMenuThongTin.add(lblTenTop);

		/*
		 * JButton btnExitt = new JButton("EXIT"); btnExitt.setFont(new Font("Tahoma",
		 * Font.PLAIN, 30)); btnExitt.setBackground(Color.RED); btnExitt.setBounds(32,
		 * 450, 132, 67); panelMenuThongTin.add(btnExitt);
		 * 
		 * btnExitt.addMouseListener(new MouseAdapter() { // click
		 * 
		 * @Override public void mouseClicked(MouseEvent e) { System.exit(0); } });
		 */

	}

	private void batPhim() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (huongDi != 1 && (e.getKeyChar() == 's' || e.getKeyChar() == 'S' || e.getKeyCode() == KeyEvent.VK_DOWN) )
					huongDi = 0;
				else if (huongDi != 0 && (e.getKeyChar() == 'w' || e.getKeyChar() == 'W' ||  e.getKeyCode() == KeyEvent.VK_UP))
					huongDi = 1;
				else if (huongDi != 2 && (e.getKeyChar() == 'd' || e.getKeyChar() == 'D' || e.getKeyCode() == KeyEvent.VK_RIGHT))
					huongDi = 3;
				else if (huongDi != 3 && (e.getKeyChar() == 'a' || e.getKeyChar() == 'A' || e.getKeyCode() == KeyEvent.VK_LEFT))
					huongDi = 2;
			}
		});
	}

	private void capNhatThongTin() {
		this.lblDiemSo.setText(String.valueOf(diem));
	}

	private void kiemTraAnThucAn() {
		if ((this.listToaDo.get(0).getX() == toaDoThucAn.getX() && this.listToaDo.get(0).getY() == toaDoThucAn.getY()))
			this.isThucAn = true;
	}

	private Point thucAn() {
		int ranDomX = 0 + (int) (Math.random() * 560);
		int ranDomY = 0 + (int) (Math.random() * 580);
		return new Point((ranDomX - (ranDomX % 20)), (ranDomY - (ranDomY % 20)));
	}

	private boolean thua() {
		Point t = new Point(listToaDo.get(0));
		if (t.getX() < 0 || t.getX() > 560 || t.getY() < 0 || t.getY() > 580)
			return true;
		for (int i = 3; i < listToaDo.size(); i++)
			if (listToaDo.get(0).soSanhHaiDiem(listToaDo.get(i)))
				return true;
		return false;
	}

	private void run() {
		batPhim();
		// hien thi thuc an
		toaDoThucAn = thucAn();
		hienThiThucAn.setHorizontalAlignment(SwingConstants.CENTER);
		hienThiThucAn.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/thucAn.png")));
		hienThiThucAn.setBounds(toaDoThucAn.getX(), toaDoThucAn.getY(), 20, 20);
		lblThamCo.add(hienThiThucAn);
		// dau ran
		dauRan = new JLabel("");
		dauRan.setHorizontalAlignment(SwingConstants.CENTER);
		dauRan.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/dauRan.png")));
		dauRan.setBounds(chiDuong.getX(), chiDuong.getY(), 20, 20);
		listNot.add(0, dauRan);
		listToaDo.add(0, chiDuong);
		lblThamCo.add(listNot.get(0));
		// vong lap game
		Timer t = new java.util.Timer();
		t.schedule(new TimerTask() {

			@Override
			public void run() {
				switch (huongDi) {
				case 0:
					chiDuong.xuong();
					break;
				case 1:
					chiDuong.len();
					break;
				case 2:
					chiDuong.quaTrai();
					break;
				case 3:
					chiDuong.quaPhai();
					break;
				}
				// an duoc thuc an
				kiemTraAnThucAn();
				if (isThucAn == true) {
					toaDoThucAn = thucAn();
					hienThiThucAn.setBounds(toaDoThucAn.getX(), toaDoThucAn.getY(), 20, 20);
					diem += 5;
				}
				chiDuongTam = new Point(chiDuong);
				dauRan = new JLabel("");
				dauRan.setHorizontalAlignment(SwingConstants.CENTER);
				dauRan.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/dauRan.png")));
				dauRan.setBounds(chiDuongTam.getX(), chiDuongTam.getY(), 20, 20);
				listToaDo.add(0, chiDuongTam);

				listNot.add(dauRan);
				// cap nhat thong tin
				capNhatThongTin();
				// pham quy ket thuc game
				boolean flag = thua();
				if (flag == true) {
					t.cancel();
					hienThiGiaoDienKetThuc();
				}
				listNot.get(listNot.size() - 1)
						.setIcon(new ImageIcon(MainFrame.class.getResource("/icon/thanRan.png")));
				for (JLabel addNot : listNot)
					lblThamCo.add(addNot);
				if (isThucAn == false && flag != true) {
					lblThamCo.remove(1);
					listNot.remove(listNot.size() - 1);
					listToaDo.remove(listToaDo.size() - 1);
				} else
					isThucAn = false;
				for (int i = 0; i < listNot.size(); i++)
					listNot.get(i).setBounds(listToaDo.get(i).getX(), listToaDo.get(i).getY(), 20, 20);
			}
		},0, 100);
	}

	private void docFile() {
		try (Scanner scanner = new Scanner(
				new File("/icon/top.txt"))) {
			String s = scanner.nextLine();
			String[] token = s.split("#");
			tenHang = token[0];
			diemHang = Integer.valueOf(token[1]);
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("loi File");
			e.printStackTrace();
		}
	}

	private void ghiFile(String ten, int diem) throws IOException {
		File f = new File("/icon/top.txt");
		PrintWriter writer = new PrintWriter(f);
		writer.print(ten + "#" + diem);
		writer.close();
	}

}
