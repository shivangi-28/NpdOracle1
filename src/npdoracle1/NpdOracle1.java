//npd to DB communication
import java.sql.*;
class NpdOracle 
{
	public static void main(String[] args) 
	{
		Connection ncon=null,ocon=null;
		Statement st1=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			ncon=DriverManager.getConnection("jdbc:odbc:txtdsn");
			ocon=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","scott","tiger");
			st1= ncon.createStatement();
			pst=ocon.prepareStatement("insert into student values (?,?,?)");
			String sno,sname,add;
			 rs=st1.executeQuery("select * from test.tab");
			while(rs.next())
			{
				 sno=rs.getString(1);
				 sname=rs.getString(2);
				 add=rs.getString(3);
				 pst.setInt(1,Integer.parseInt(sno.trim()));
				 pst.setString(2,sname);
				 pst.setString(3,add);
				 pst.executeUpdate();
			}//while

			System.out.println("Data inserted");
           rs.close();
			st1.close();
			pst.close();
			ncon.close();
			ocon.close();
		}//try