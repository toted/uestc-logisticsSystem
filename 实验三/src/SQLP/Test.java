package SQLP;

public class Test 
{
	public static void main(String[] args)
	{
		sente_pre pre=new sente_pre();
		//String sql="UPDATE STAFFS SET SALARY=SALARY - 1000 WHERE SECTION_ID = 'x';";
		//String sql="INSERT INTO STAFFS VALUES(2051, 'Shelley', 'Higgins', 'SHIGGINS', '515.123.8080', to_date('07-06-1994', 'dd-mm-yyyy'), 'AC_MGR', 12000.00, null, 101, 110);";
		//String sql="select y from edr where x = '8';";
		String sql="CREATE TABLE employment_history ( Staff_id  NUMBER,Start_date  DATE, End_date  DATE,Employment_id  VARCHAR,Section_id NUMBER);";
		//String sql="SELECT COUNT,STATE_ID FROM PLACES where x = '16';";
		pre.judge(sql);
	}

}
