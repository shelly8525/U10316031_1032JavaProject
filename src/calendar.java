
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.GregorianCalendar;
 
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
 
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
		
		//下拉表示的月份
		monthBox.setSelectedIndex(now_month);
		
		//排下拉表跟按鈕的版面
		JPanel panel1 = new JPanel();
		panel1.add(yearLabel);
		panel1.add(yearBox);
		panel1.add(monthLabel);
		panel1.add(monthBox);
		panel1.add(OkButton);
		panel1.add(TodayButton);
		
		//設按鈕的監聽器
		OkButton.addActionListener(this);
		TodayButton.addActionListener(this);
		
		//放置日期面板
		JPanel panel_day = new JPanel();
		panel_day.setLayout(new GridLayout(7,7,3,3));
		//設置星期名稱
		for(int i2 = 0;i2 < 7;i2++){
			button_week[i2] = new JButton(" ");
			button_week[i2].setText(week[i2]);
			button_week[0].setForeground(Color.black);
			panel_day.add(button_week[i2]);			
		}
		button_week[0].setForeground(Color.red);
		button_week[6].setForeground(Color.red);
		
		//設置日期到面板李
		for(int i3 = 0;i3 < 42;i3++){
			button_day[i3] = new JButton(" ");
			panel_day.add(button_day[i3]);
		}
		
		//呼叫paintDay方法
		this.paintDay();
		
		//把剛剛設的面板放上來
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		panel2.add(panel_day,BorderLayout.SOUTH);
		panel2.add(panel1,BorderLayout.NORTH);
		getContentPane().add(panel2);
	}
	
	private void paintDay(){
		if(todayFlag){
			//顯示今天的日期 沒有點選其他日期時
			year_int = now_year + "";
            month_int = now_month;
		}
		else{
			//選擇日期
			year_int = yearBox.getSelectedItem().toString();
			month_int = monthBox.getSelectedIndex();
		}
		
		int year_sel = Integer.parseInt(year_int) - 1900;
		//找到第一天
		Date firstDay = new Date(year_sel,month_int,1);
		//建一個calendar
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(firstDay);
		int days = 0;//那個月有幾天
		int day_week = 0;
		
		//每個月有幾天
		if(month_int == 0 || month_int == 2 || month_int == 4 || month_int == 6 ||  month_int == 7 ||  month_int == 9 ||  month_int == 11){
			days = 31;
		}
		else if(month_int == 3 || month_int == 5 || month_int == 8 || month_int == 10){
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
		//getDay()方法 第一天是星期幾
		day_week = firstDay.getDay();
		int count = 1;
		
		//每天的顏色
		for(int i = day_week;i <day_week + days;count++,i++){
			if(i % 7 == 0 || i == 6 || i == 13 || i == 20 || i == 27 || i == 34 || i == 41){
				//今天設藍色
				if(i == day_week + now_Date.getDate() - 1){
					button_day[i].setForeground(Color.blue);
					button_day[i].setText(count + "");
				}
				else{
					//其他日期用紅色
					button_day[i].setForeground(Color.red);
					button_day[i].setText(count + "");
				}
			}
			else{
				if(i == day_week + now_Date.getDate() - 1){
					button_day[i].setForeground(Color.blue);
					button_day[i].setText(count + "");
				}
			}
		}
		
		//沒有日期的空白
		if(day_week == 0){
			//如果第一天是周日,後面空白的地方
			for(int i = days;i < 42;i++){
				button_day[i].setText(" ");
			}
		}
		else{
			//如果第一天不是周日,前面空白的地方
			for(int i = 0;i <day_week;i++){
				button_day[i].setText(" ");
			}//後面空白的地方
			for(int i = day_week + days;i < 42;i++){
				button_day[i].setText(" ");
			}
		}
		}
	
	
	public void actionPerformed(ActionEvent e){
		//查看的功能
		if(e.getSource() == OkButton){
			todayFlag = false;
			this.paintDay();
		}//今天的功能
		else if(e.getSource() == TodayButton){
			todayFlag = true;
			yearBox.setSelectedIndex(10);
			monthBox.setSelectedIndex(now_month);
			this.paintDay();
		}
	}
	public static void main(String[] args){
		calendar frame = new calendar();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
}
