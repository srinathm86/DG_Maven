
package com.ibsplc.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;



/**
 *
 * @author a-7868
 *
 */
public class HTMLReport2 {

	static String htmlDir="/reports/CustomReport/";
	static String OUT_FOLDER ="";
	static String imgFolder = "/reports/img/";
	static String fileName = "CustomReport";
	static BufferedWriter out = null;
	static String logo = "../img/iCargo_logo.jpg";
	final static Logger logger = Logger.getLogger(HTMLReport2.class);


	public static void customHTMLReport(String html_class, String html_method, boolean isFinal) throws IOException{


		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HHmmss");
		String todaysDate = sdf.format(currentDate.getTime());
		String dateTimeStamp = dateFormat.format(currentDate.getTime());

		if(isFinal)
			ReportValues.reportFileName = fileName+" "+dateTimeStamp;
		else
			ReportValues.reportFileName = "interimReport";
		
		imgFolder = new File(".").getCanonicalPath()+ imgFolder;
		//logo	= imgFolder+"\\iCargo_logo.jpg";
		OUT_FOLDER = new File(".").getCanonicalPath()+ htmlDir;
		PrintWriter fstream = new PrintWriter(new BufferedWriter(
				new FileWriter(new File(OUT_FOLDER, ReportValues.reportFileName+".html"))));
		out = new BufferedWriter(fstream);

		out.write("<!DOCTYPE html>"+
					"<html lang='en'>"+
					"<head>"+
					  "<title>iCargo Report</title>"+
					  "<meta charset='utf-8'>"+
					  "<meta name='viewport' content='width=device-width, initial-scale=1'>"+
					  "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>"+
					  "<link rel='stylesheet' href='../img/w3.css'>"+
					  "<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js'></script>"+
					  "<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js'></script>"+
					"</head>"+
					
					"<style>"+
					    /* Set height of the grid so .sidenav can be 100% (adjust if needed) */
					    ".row.content {height: 800px;}"+
					    
					    /* Set gray background color and 100% height */
					    ".sidenav {"+
					    "  background-color: #f3f3f3;"+
					    "  height: 100%;"+
					    "}"+
					    
					    /* Set black background color, white text and some padding */
					    "footer {"+
					    "  background-color: #555;"+
					    "  color: white;"+
					    "  padding: 15px;"+
					    "}"+
					    
					    /* On small screens, set height to 'auto' for sidenav and grid */
					    "@media screen and (max-width: 767px) {"+
					    "  .sidenav {"+
					    "    height: auto;"+
					    "    padding: 15px;"+
					    "  }"+
					    "  .row.content {height: auto;} "+
					    "}"+
						
						"table #t1 {"+
					    "border-collapse: collapse;"+
					    "width: 100%;"+
						"}"+
					
						"table#t1 th, table#t1 td {"+
						"	padding: 5px;"+
						"	text-align: left;"+
						"	border-bottom: 1px solid #ddd;"+
						"}"+
					
						".nav-pills>li.active>a, .nav-pills>li.active>a:hover, .nav-pills>li.active>a:focus{"+
						"	background-color:#f3f3f3;"+
						"	color:black;"+
						"}"+
						
						"li.active>a{"+
						"	background-color:#f3f3f3;"+
						"	color:black;"+
						"}"+
						".nav-pills>li>a {"+
						"	color:rgba(0,0,0,.5);padding:1px 10px;"+
						"}"+
						
						".overlay {"+
						"	position: fixed;"+    
						"	display: none;"+
						"	align: center; "+   
						"	width: 100%;"+ 
						"	height: 100%;"+   
						"	top: 0; "+  
						"	left: 0; "+   
						"	right: 0; "+   
						"	bottom: 0;"+   
						"	background-color: "+
						"	rgba(0,0,0,0.8); "+   
						"	z-index: 2;	"+ 
						"	transition: 2s ease; "+  
						"	cursor: pointer;"+
						"}"+
						
						".text{  "+  
						"	position: absolute; "+   
						"	top: 50%;    "+
						"	left: 50%;    "+
						"	font-size: 15px;  "+  
						"	color: white;  "+  
						"	transform: translate(-50%,-50%);  "+  
						"	-ms-transform: translate(-50%,-50%);"+
						"}"+
							
					  "</style>"+
					  
						"<script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>"+
						"<script type='text/javascript'>"+
						"	google.charts.load('current', {packages:['corechart']});"+
						"	google.charts.setOnLoadCallback(drawChart);"+
						"	function drawChart() {"+
						"		var data = google.visualization.arrayToDataTable([['Status', 'TCs', { role: 'style' }],['Pass', "+ReportValues.getPassCount()+", 'green'],['Fail', "+ReportValues.getFailCount()+", 'red'],['Block',"+ReportValues.getSuiteSkipCount()+", 'orange'], ]);"+
						"		var options = {title: '', 'width': 600, 'height':300, backgroundColor:'none', is3D: true, slices: {0:{color:'green'}, 1:{color:'red'}, 2:{color:'orange'}}};"+ 
						"		var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));chart.draw(data, options);"+ 
						"		var options = {title: '', 'width': 600, 'height':300};"+ 
						"		var chart2 = new google.visualization.ColumnChart(document.getElementById('barChart'));chart2.draw(data, options);"+ 
						"	}"+
						"</script>"+
						"<script>function on(id) {    document.getElementById(id).style.display = 'block';}function off(id) {    document.getElementById(id).style.display = 'none';}"+
						"</script>"+
					  
					"<body>"+
					
					"<div class='container-fluid'>"+
						"<div class='col-sm-3 sidenav'>"+
						"	<div class='row content', data-spy='affix'>"+
						"	  <br>"+
						"	  <div class='well well-sm title'>"+
						"	  <center><img style='border-radius: 40px;'src='../img/iCargo_logo.jpg' width=120px height=90px/></center>"+
						"		<center><h3 style='color:#337ab7;'>iCargo Automation Report</h3></center>"+
						"	  </div>"+	
						"	  <div style='overflow-x:scroll;'>"+	
						"	  <ul class='nav nav-pills nav-stacked'>"+
						"		<li class='active'><a data-toggle='tab' href='#details'>Execution Details</a></li><li class='nav-divider'></li>"+
						"		<li><a data-toggle='tab' href='#summary'>Test Summary</a></li><li class='nav-divider'></li>"+
						"		<li><a data-toggle='tab' href='#modSummary'>Module Summary</a></li><li class='nav-divider'></li>"+
					
						"			<li><a href='#'>Modules</a></li>"+
					/*	"				<div style='padding-left:30px;'>"+
						"					<ul id='drilldown-1' class='nav nav-pills nav-stacked collapse'>		"*/"");
		
						for(String className : ReportValues.classStat.keySet()){
							
							out.write("<li style='padding-left:10px;font-size:12px;'><a data-toggle='tab' href='#"+className+"'>"+className+"</a></li>");
						}
											                     
						out.write(/*"			</ul>"+
						"				</div>"+
						"			</li>"+*/
						"		</li>"+
						"	  </ul></div>"+
						"	</div>"+
						"</div>"+
						
						"<div class='col-sm-9'>"+
						"	  <div class='tab-content'>"+
								
						"		<div id='details' class='tab-pane fade in active'>"+
								  
						"			<br>"+
						"			<div class='well well-sm'><center><h2 style='color:#337ab7;'>Execution Details</h2></center></div>"+
						"			<table  class='table table-striped table-bordered table-hover table-condensed' >"+
						"				<tr> "+
						"					<td>"+
						"						<FONT COLOR=#000100 FACE=Arial SIZE=2>"+
						"						<b>Description</b>"+
						"					</td>"+
						"					<td>"+
						"						<FONT COLOR=#000100 FACE=Arial SIZE=2>"+
						"						<b>Result</b>"+
						"					</td>"+
						"				</tr>"+
						"				<tr> "+
						"					<td align=left width=5%>"+
						"						Implementation"+
						"					</td>"+
						"					<td align=left width=5%>"+
											ReportValues.getImplementation()+
						"					</td>"+
						"				</tr>"+
						"				<tr> "+
						"					<td align=left width=5%>"+
						"						Suite"+
						"					</td>"+
						"					<td align=left width=5%>"+
											ReportValues.getSuiteName()+
						"					</td>"+
						"				</tr>"+
						"				<tr> "+
						"					<td align=left width=5%>"+
						"						App URL"+
						"					</td>"+
						"					<td align=left width=5%>"+
						"						<a href='"+ReportValues.getAppURL()+"' target='_blank'>"+ReportValues.getAppURL()+"</a>"+
						"					</td>"+
						"				</tr>"+
						"				<tr> "+
						"					<td align=left width=5%>"+
						"						App User"+
						"					</td>"+
						"					<td align=left width=5%>"+
											ReportValues.getUserName()+
						"					</td>"+
						"				</tr>"+
						"				<tr> "+
						"					<td align=left width=5%>"+
						"						App Version"+
						"					</td>"+
						"					<td align=left width=5%>"+
											ReportValues.getAppVersion()+
						"					</td>"+
						"				</tr>"+
						"				<tr> "+
						"					<td align=left width=5%>"+
						"						Date"+
						"					</td>"+
						"					<td align=left width=5%>"+
											ReportValues.getExecDate()+
						"					</td>"+
						"				</tr>"+
						"				<tr> "+
						"					<td align=left width=5%>"+
						"						Start Time"+
						"					</td>"+
						"					<td align=left width=5%>"+
											ReportValues.getTestStart()+
						"					</td>"+
						"				</tr>"+
						"				<tr> "+
						"					<td align=left width=5%>"+
						"						End Time"+
						"					</td>"+
						"					<td align=left width=5%>"+
											ReportValues.getTestEnd()+
						"					</td>"+
						"				</tr>"+
						"				<tr> "+
						"					<td align=left width=5%>"+
						"						Total TCs"+
						"					</td>"+
						"					<td align=left width=5%>"+
											ReportValues.getSuiteTotalCount()+
						"					</td>"+
						"				</tr>"+
						"				<tr> "+
						"					<td align=left width=5%>"+
						"						Passed TCs"+
						"					</td>"+
						"					<td align=left width=5%>"+
											ReportValues.getPassCount()+
						"					</td>"+
						"				</tr>"+
						"				<tr> "+
						"					<td align=left width=5%>"+
						"						Failed TCs"+
						"					</td>"+
						"					<td align=left width=5%>"+
											ReportValues.getFailCount()+
						"					</td>"+
						"				</tr>"+
						"				<tr> "+
						"					<td align=left width=5%>"+
						"						Blocked TCs"+
						"					</td>"+
						"					<td align=left width=5%>"+
											ReportValues.getSuiteSkipCount()+
						"					</td>"+
						"				</tr>"+
						"				<tr> "+
						"					<td align=left width=5%>"+
						"						Duration"+
						"					</td>"+
						"					<td align=left width=5%>"+
											(int)(ReportValues.getExecTime()/1000/60/60)+"h "+((int)ReportValues.getExecTime()/1000/60)%60+"m"+
						"					</td>"+
						"				</tr>"+
						"				<tr> "+
						"					<td align=left width=5%>"+
						"						Browser"+
						"					</td>"+
						"					<td align=left width=5%>"+
											ReportValues.getBrowserName()+
						"					</td>"+
						"				</tr>"+
						"				<tr> "+
						"					<td align=left width=5%>"+
						"						User"+
						"					</td>"+
						"					<td align=left width=5%>"+
											System.getProperty("user.name")+
						"					</td>"+
						"				</tr>"+
						"				<tr> "+
						"					<td align=left width=5%>"+
						"						Execution Log"+
						"					</td>"+
						"					<td align=left width=5%>"+
						"						<a href='../execution_log.log' target='_blank'>Execution.log</a>"+
						"					</td>"+
						"				</tr>"+
						"			</table>	"+		  
						"		</div>"+
								
						"		<div id='summary' class='tab-pane fade'><br>"+
						
						"			<div class='well well-sm'><center><h2 style='color:#337ab7;'>Test Summary</h2></center></div><br>"+
						"		  	<center><div id='piechart_3d' style='width: 600px; height: 300px;'></div></center>"+
						"		  	<center><div id='barChart' style='width: 600px; height: 300px;'></div></center>"+
						"		</div>"+
								
						"		<div id='modSummary' class='tab-pane fade'>"+
								  
						"			<br>"+
						"			<div class='well well-sm'><center><h2 style='color:#337ab7;'>Module Summary</h2></center></div>"+
						"			<table class='table table-striped table-bordered table-hover table-condensed' >"+
						"			<tr> "+
						"				<td align=center width=20% >"+
						"					<b>Module</b>"+
						"				</td>"+
						"				<td align=center width=20% >"+
						"					<b>Parent Module</b>"+
						"				</td>"+
						"				<td align=center width=15% >"+
						"					<b>TC Count</b>"+
						"				</td>"+
						"				<td align=center width=15% >"+
						"					<b>Pass Count</b>"+
						"				</td>"+
						"				<td align=center width=15% >"+
						"					<b>Fail Count</b>"+
						"				</td>"+
						"				<td align=center width=15% >"+
						"					<b>Block Count</b>"+
						"				</td>"+
						"			</tr>"+
						
									//class summary
									html_class +
									
						"		  </table>"+
						"		</div>"+
								
								//method summary
								html_method +
							  
						"	  </div>"+
						"</div>"+
						
					"</div>"+
					"<center>"
					+ "<div class='w3-container'>"
					+ "<div id='modal01' class='w3-modal' onclick=\"this.style.display='none'\">"
					+ "<span class='w3-button w3-hover-red w3-xlarge w3-display-topright'>&times;</span>"
					+ "<div class='w3-modal-content w3-animate-zoom'>"
					+ "<img id=img1 src='img_fjords.jpg' style='width:100%'>"
					+ "</div>"
					+ "</div>"
					+ "</div>"+
					"</body>"+
					"</html>"	);
		
		out.close();

		logger.info("Report written to HTML File - "+ReportValues.reportFileName);
	}

	public static void customPDFReport() throws DocumentException, IOException {

		System.out.println(OUT_FOLDER);
		/*Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(new File(OUT_FOLDER, fileName+".pdf")));
		document.open();
		HTMLWorker htmlWorker = new HTMLWorker(document);
//        XMLWorkerHelper.getInstance().parseXHtml(writer, document,
//                new FileInputStream("index.html"));
		Reader reader = new FileReader(OUT_FOLDER+"/"+fileName+".html");
		htmlWorker.parse(reader);
		document.close();
		*/
	}

	
}



 