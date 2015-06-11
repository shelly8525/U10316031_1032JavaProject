
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class calendar extends JFrame implements ActionListener {
	//月份跟年份的下拉表
	private JComboBox yearBox = new JComboBox();
	private JComboBox monthBox = new JComboBox();
	//月份跟年份的標籤
	private JLabel yearLabel = new JLabel("年分");
	private JLabel monthLabel = new JLabel("月份");
	//查看跟今天的按鈕
	private JButton OkButton = new JButton("查看");
	private JButton TodayButton = new JButton("今天");
	//取得今天的日期 年分 月份
	private Date now_Date = new Date();
	private int now_year = now_Date.getYear() + 1900;
	private int now_month = now_Date.getMonth();
	//是否顯示今天的日期
	private boolean todayFlag = false;
	//用一組按鈕顯示日期,共7行7列,第1烈是星期
	private JButton[] button_day = new JButton[42];
	private final String[] week = {"日","一","二","三","四","五","六"};
	private JButton[] button_week = new JButton[7];
	//保存使用者選擇的年份
	private String year_int = null;
	//保存使用者選擇的月份
	private int month_int;
	//建構子
	public calendar(){
		super();
		this.setTitle("日曆");
		this.init();
		this.setLocation(500,300);
		this.setResizable(false);
		pack();
	}
	
	//更新日期
	private void init(){
		Font font = new Font("Dialog", Font.BOLD, 14);
		yearLabel.setFont(font);
		monthLabel.setFont(font);
		OkButton.setFont(font);
		TodayButton.setFont(font);
		
		//定年份,過前10年到未來20年
		for(int i = now_year - 10;i <= now_year + 20;i++){
			yearBox.addItem(i +"");
		}
		
		//下拉時表示多少年
		yearBox.setSelectedIndex(10);
		
		//定月份
		for(int i1 = 1;i1 < 13;i1++){
			monthBox.addItem(i1+"");
		}
		
		//�U������������
		monthBox.setSelectedIndex(now_month);
		
		//���U���������s������
		JPanel panel1 = new JPanel();
		panel1.add(yearLabel);
		panel1.add(yearBox);
		panel1.add(monthLabel);
		panel1.add(monthBox);
		panel1.add(OkButton);
		panel1.add(TodayButton);
		
		//�]���s��������
		OkButton.addActionListener(this);
		TodayButton.addActionListener(this);
		
		//���m�������O
		JPanel panel_day = new JPanel();
		panel_day.setLayout(new GridLayout(7,7,3,3));
		//�]�m�P���W��
		for(int i2 = 0;i2 < 7;i2++){
			button_week[i2] = new JButton(" ");
			button_week[i2].setText(week[i2]);
			button_week[0].setForeground(Color.black);
			panel_day.add(button_week[i2]);			
		}
		button_week[0].setForeground(Color.red);
		button_week[6].setForeground(Color.red);
		
		//�]�m���������O��
		for(int i3 = 0;i3 < 42;i3++){
			button_day[i3] = new JButton(" ");
			panel_day.add(button_day[i3]);
		}
		
		//�I�spaintDay���k
		this.paintDay();
		
		//�������]�����O���W��
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		panel2.add(panel_day,BorderLayout.SOUTH);
		panel2.add(panel1,BorderLayout.NORTH);
		getContentPane().add(panel2);
	}
	
	private void paintDay(){
		if(todayFlag){
			//�������������� �S���I�����L������
			year_int = now_year + "";
            month_int = now_month;
		}
		else{
			//��������
			year_int = yearBox.getSelectedItem().toString();
			month_int = monthBox.getSelectedIndex();
		}
		
		int year_sel = Integer.parseInt(year_int) - 1900;
		//�������@��
		Date firstDay = new Date(year_sel,month_int,1);
		//���@��calender
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(firstDay);
		int days;//���������X��
		int day_week = 0;
		
		//�C�������X��
		if(month_int == 0 || month_int == 2 || month_int == 4 || month_int == 6 ||  month_int == 7 ||  month_int == 9 ||  month_int == 11){
			days = 31;
		}
		else if(month_int == 3 ||�@month_int ==�@5�@|| month_int == 8 || month_int == 10){
			days = 30;
		}
		else{
			if(cal.isLeapYear(year_sel)){
				days = 29;
			}
			else{
				days = 28;
			}
		}
		//getDay()���k ���@���O�P���X
		day_week = firstDay.getDay();
		int count = 1;
		
		//�C�����C��
		for(int i = day_week;i <�@day_week + days;count++,i++){
			if(i % 7 == 0 || i == 6 || i == 13 || i == 20 || i == 27 || i == 34 || i == 41){
				//�����]����
				if(i == day_week = now_date.getDate() - 1){
					button_day[i].setForeground(Color.blue);
					button_day[i].setText(count + "");
				}
				else{
					//���L����������
					button_day[i].setForeground(Color.red);
					button_day[i].setText(count + "");
				}
			}
			else{
				if(i == day_week + now_date.getDate() - 1){
					button_day[i].setForeground(Color.blue);
					butTexton_day[i].setText(count + "");
				}
			}
		}
		
		//�S������������
		if(day_week == 0){
			//�p�G���@���O�P��,�����������a��
			for(int i = days;i <�@42;i++){
				buttonn_day[i].setText(" ");
			}
		}
		else{
			//�p�G���@�����O�P��,�e���������a��
			for(int i = 0;i <day_week;i++){
				button_day[i].setText("�@");
			}//�����������a��
			for(int i = day_week + days;i < 42;i++){
				button_days[i].setText(" ");
			}
		}
		}
	
	
	public void actionPerformed(ActionEvent e){
		
	}
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
}
