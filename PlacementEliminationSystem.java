import java.sql.*;
public class PlacementEliminationSystem{
public static void main(String args[]) throws Exception{
Class.forName("com.mysql.cj.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/PlacementEliminationSystem","root","root");
Statement st=con.createStatement();
ResultSet rs=st.executeQuery("select * from students");
int ids[]=new int[20];
String names[]=new String[20];
double r1scores[]=new double[20];
double r2scores[]=new double[20];
double r3scores[]=new double[20];
double finalscores[]=new double[20];
String companies[]=new String[20];
String status[]=new String[20];
int round1eliminated=0;
int round2eliminated=0;
int round3eliminated=0;
int selected=0;
int rejected=0;
int totalstudents=0;
double highest=0;
double lowest=9999;
String companyNames[]=new String[10];
int technical[]=new int[10];
int communication[]=new int[10];
int projectcutoff[]=new int[10];
int hiring[]=new int[10];
int companycount[]=new int[10];
Statement cst=con.createStatement();
ResultSet crs=cst.executeQuery("select * from companies");
int c=0;
while(crs.next()){
companyNames[c]=crs.getString("company_name");
technical[c]=crs.getInt("technical_cutoff");
communication[c]=crs.getInt("communication_cutoff");
projectcutoff[c]=crs.getInt("project_cutoff");
hiring[c]=crs.getInt("max_hiring");
c++;
}
int i=0;
System.out.println("========");
System.out.println("PLACEMENT ELIMINATION REPORT");
System.out.println("========");
while(rs.next()){
totalstudents++;
int id=rs.getInt("id");
String name=rs.getString("name");
int coding=rs.getInt("coding");
int aptitude=rs.getInt("aptitude");
int communicationmarks=rs.getInt("communication");
int projects=rs.getInt("projects");
int hackathons=rs.getInt("hackathons");
double round1=(coding*0.5)+(projects*20)+(hackathons*10);
if(round1<70){
ids[i]=id;
names[i]=name;
r1scores[i]=round1;
status[i]="ELIMINATED ROUND-1";
companies[i]="NONE";
round1eliminated++;
rejected++;
}
else{
double round2=(communicationmarks*0.6)+(aptitude*0.4);
if(round2<75){
ids[i]=id;
names[i]=name;
r1scores[i]=round1;
r2scores[i]=round2;
status[i]="ELIMINATED ROUND-2";
companies[i]="NONE";
round2eliminated++;
rejected++;
}
else{
double round3=(coding+aptitude+communicationmarks)/3.0;
if(round3<80){
ids[i]=id;
names[i]=name;
r1scores[i]=round1;
r2scores[i]=round2;
r3scores[i]=round3;
status[i]="ELIMINATED ROUND-3";
companies[i]="NONE";
round3eliminated++;
rejected++;
}
else{
double finalscore=(round1*0.5)+(round2*0.3)+(round3*0.2);
if(finalscore>highest){
highest=finalscore;
}
if(finalscore<lowest){
lowest=finalscore;
}
String allocatedcompany="NOT SELECTED";
String finalstatus="REJECTED";
for(int j=0;j<c;j++){
if(finalscore>=technical[j]&&communicationmarks>=communication[j]&&projects>=projectcutoff[j]&&hiring[j]>0){
allocatedcompany=companyNames[j];
finalstatus="SELECTED";
hiring[j]--;
companycount[j]++;
selected++;
break;
}
}
if(finalstatus.equals("REJECTED")){
rejected++;
}
ids[i]=id;
names[i]=name;
r1scores[i]=round1;
r2scores[i]=round2;
r3scores[i]=round3;
finalscores[i]=finalscore;
companies[i]=allocatedcompany;
status[i]=finalstatus;
}
}
}
PreparedStatement ps=con.prepareStatement("insert into interview_results values(?,?,?,?,?,?,?,?)");
ps.setInt(1,ids[i]);
ps.setString(2,names[i]);
ps.setDouble(3,r1scores[i]);
ps.setDouble(4,r2scores[i]);
ps.setDouble(5,r3scores[i]);
ps.setDouble(6,finalscores[i]);
ps.setString(7,companies[i]);
ps.setString(8,status[i]);
ps.executeUpdate();
i++;
}
System.out.println("ID\tNAME\tR1\tR2\tR3\tFINAL\tCOMPANY\tSTATUS");
for(int j=0;j<i;j++){
System.out.println(ids[j]+"\t"+names[j]+"\t"+r1scores[j]+"\t"+r2scores[j]+"\t"+r3scores[j]+"\t"+finalscores[j]+"\t"+companies[j]+"\t"+status[j]);
}
System.out.println("=================================================");
System.out.println("ROUND-1 ELIMINATED : "+round1eliminated);
System.out.println("ROUND-2 ELIMINATED : "+round2eliminated);
System.out.println("ROUND-3 ELIMINATED : "+round3eliminated);
System.out.println("TOTAL SELECTED : "+selected);
System.out.println("TOTAL REJECTED : "+rejected);
System.out.println("TOTAL STUDENTS : "+totalstudents);
System.out.println("HIGHEST FINAL SCORE : "+highest);
System.out.println("LOWEST FINAL SCORE : "+lowest);
System.out.println("=================================================");
System.out.println("COMPANY WISE COUNT");
for(int j=0;j<c;j++){
System.out.println(companyNames[j]+" : "+companycount[j]);
}
con.close();
}
}