package com.informatica.web.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.aventstack.extentreports.ExtentTest;
import com.informatica.tests.BaseTest;

public class ReportGenerator {

	private void getTotalTestCount() {

		StringBuilder totalValue = new StringBuilder();
		BufferedWriter testCasesCountWriter = null;
		try {

			testCasesCountWriter = new BufferedWriter(new FileWriter("TotalTestCountDetails.txt"));
			Map<String, ExtentTest> extentTestMap = BaseTest.getExtentTestMap();
			int totalPass = 0; int totalFail = 0; int totalSkip = 0;
			Iterator<Entry<String, ExtentTest>> it = extentTestMap.entrySet().iterator();
			while (it.hasNext()) {
				ExtentTest extentTest = it.next().getValue();
				if (extentTest.getModel() != null) {
					if(extentTest.getModel().getStatus().toString().equalsIgnoreCase("PASS")) {
						totalPass = totalPass + 1;
					}else if(extentTest.getModel().getStatus().toString().equalsIgnoreCase("FAIL")) {
						totalFail = totalFail + 1;
					}else if(extentTest.getModel().getStatus().toString().equalsIgnoreCase("SKIP")) {
						totalSkip =+ 1;
					}
				}
			}
			int grandTotal = totalPass + totalFail + totalSkip;
			totalValue.append(grandTotal + ":" + totalPass + ":" + totalFail + ":" + totalSkip);
			totalValue.append("\n");
			testCasesCountWriter.write(totalValue.toString());
			testCasesCountWriter.close();
		} catch (Exception e) {

		}
	}
	
	private void getTestCountByModule() {
		
		Map<String, ExtentTest> extentTestMap = BaseTest.getExtentTestMap();
		HashMap<String, int[]> allTestDetails = new HashMap<String, int[]>();
		Iterator<Entry<String, ExtentTest>> it = extentTestMap.entrySet().iterator();
		while(it.hasNext()) {
			ExtentTest extentTest = it.next().getValue();
			if(extentTest.getModel().getCategoryList().get(0) != null) {
				allTestDetails.put(extentTest.getModel().getCategoryList().get(0).getName().toString(), new int[3]);
			}
		}
		
		Iterator<Entry<String, ExtentTest>> iterator = extentTestMap.entrySet().iterator();
		while(iterator.hasNext()) {
			ExtentTest extentTest = iterator.next().getValue();
			String mainCategoryName = extentTest.getModel().getCategoryList().get(0).getName();
			String testStatus = extentTest.getModel().getStatus().toString();
			Iterator<Entry<String, int[]>> allTestsIterator = allTestDetails.entrySet().iterator();
			while(allTestsIterator.hasNext()) {
				Entry<String, int[]> allTestsEntry = allTestsIterator.next();
				String categoryName = allTestsEntry.getKey();
				int[] testsCount = allTestsEntry.getValue();
				if(categoryName.equalsIgnoreCase(mainCategoryName)) {
					if(testStatus.equalsIgnoreCase("PASS")) {
						testsCount[0] = testsCount[0] + 1;
					}else if(testStatus.equalsIgnoreCase("FAIL")) {
						testsCount[1] = testsCount[1] + 1;
					}else if(testStatus.equalsIgnoreCase("SKIP")) {
						testsCount[2] = testsCount[2] + 1;
					}
					allTestDetails.put(categoryName, testsCount);
					break;
				}
			}
		}
		
		Iterator<Entry<String, int[]>> allTestsIterator = allTestDetails.entrySet().iterator();
		
		try {
			BufferedWriter allWriter;
			allWriter = new BufferedWriter(new FileWriter("TestCaseCountDetails.txt"));
			StringBuilder allBuilder = new StringBuilder();
			while (allTestsIterator.hasNext()) {
				Entry<String, int[]> testCountEntry = allTestsIterator.next();
				allBuilder.append(testCountEntry.getKey() + ":" + testCountEntry.getValue()[0] + ":" + testCountEntry.getValue()[1] + ":" + testCountEntry.getValue()[2]);
				allBuilder.append("\n");
			}
			allWriter.write(allBuilder.toString());
			allWriter.close();
		}catch(Exception e) {
			
		}
	}
	
	public void generateEmailableReport() {
		getTotalTestCount();
		getTestCountByModule();
		PrintWriter writerCr = null;
		try {
			writerCr = new PrintWriter("ResultMailNew.html");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		writerCr.print("<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");

		writerCr.print("<style>.body{font-size:13px;color:#330000;font-family:Monospace;word-break:break-all;}.big{font-size:16px;font-family:Courier;color:#5E6E9E;}"+
				".failed{font-size:15px;color:red;font-family:Calibri;font-weight:bold;}.skipped{font-size:15px;color:orange;font-family:Calibri;font-weight:bold;}.pass{font-size:15px;color:#66CC33;font-family:Calibri;font-weight:bold;}"+
				".total {font-size:15px;color:#330000;font-family:Calibri;font-weight:bold;}"+
				"</style></head>");		

		writerCr.print("<div>");
		writerCr.print("<table style=\"width: 1000px; color: #666666; height: 20px;\" border=\"0\" cellspacing=\"0\" cellpadding=\"2\">");
		writerCr.print("<tbody>");
		writerCr.print("<td style=\"border-bottom: solid 2px #8D99BC;\" colspan=\"2\">&nbsp;</td>");
		writerCr.print("</tr></tbody></table></div><div>");
		writerCr.print("<table style=\"width: 64%; color: #666666;\" border=\"0\" cellspacing=\"0\" cellpadding=\"3\">");
		writerCr.print("<tbody><tr bgcolor=\"#C3FDB8\">");

		String resultfile = "TotalTestCountDetails.txt";
		int totalTestCases=0;
		int totalPassedTestCases=0;
		int totalFailedTestCases=0;
		int totalSkippedTestCases=0;
		
		try {

			BufferedReader bufferedReader = new BufferedReader(new FileReader(resultfile));
			String line = null;

			while((line=bufferedReader.readLine())!=null){
				String[] listLine = line.split(":");
				totalTestCases = Integer.valueOf(listLine[0]);
				totalPassedTestCases = Integer.valueOf(listLine[1]);
				totalFailedTestCases = Integer.valueOf(listLine[2]);
				totalSkippedTestCases = Integer.valueOf(listLine[3]);
			}
			bufferedReader.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		writerCr.print("<td class=\"total\" >&nbsp; Total Testcases &nbsp;["+totalTestCases+"]</td>");
		writerCr.print("<td class=\"total\" >Passed &nbsp;  <span class=\"pass\">["+totalPassedTestCases+"] </span></td>");
		writerCr.print("<td class=\"total\" >Failed &nbsp;  <span class=\"failed\">["+totalFailedTestCases+"] </span></td>");
		writerCr.print("<td class=\"total\" >Not Executed &nbsp;  <span class=\"skipped\">["+totalSkippedTestCases+"] </span></td>");

		float testPerecentage = ((totalPassedTestCases*1f/(totalTestCases-totalSkippedTestCases)*1f)) * 100;

//		testPerecentage = (totalPassedTestCases/(totalTestCases-totalSkippedTestCases)) * 100;
		writerCr.print("<td class=\"total\" >Pass Percentage &nbsp;[ "+testPerecentage+"% ]</td>");
		writerCr.print("</tr></tbody></table></div>");
		writerCr.print("<div>");		
		writerCr.print("<table style=\"width: 1000px; color: #5e6e9e; height: 20px;\" border=\"0\" cellspacing=\"0\" cellpadding=\"3\">");
		writerCr.print("<tbody><tr>");
		writerCr.print("<td style=\"border-top: solid 2px #8D99BC;\" colspan=\"0\">&nbsp;</td>");
		writerCr.print("</tr>		</tbody>		</table>		</div>");
		
		writerCr.append("<br>");
		writerCr.print("<table cellpadding=\"3\" cellspacing=\"1\" style=\"width: 50%; color:#666666;\"><thead><tr bgcolor=\"#A569BD\" style=\"width: 300;  color:#FFFFFF; text-align: left; \">");
		writerCr.print("<th class=\"big\" style=\"color:#FFFFFF\">Suite Description</th><th class=\"big\" style=\"width:20%; text-align:center;color:#FFFFFF\">PASS </th><th class=\"big\" style=\"width:20%; text-align:center;color:#FFFFFF\">FAILED</th><th class=\"big\" style=\"width:20%; text-align:center;color:#FFFFFF\">NOT EXECUTED</th>" +
				"<th class=\"big\" style=\"width:10%; text-align:center;color:#FFFFFF\">Status </th>	</tr>	</thead>");	
		try {

			BufferedReader all = new BufferedReader(new FileReader("TestCaseCountDetails.txt"));
			String line = null;
			int i=0;
			while((line=all.readLine())!=null){
				String[] listLine = line.split(":");
				String moduleName = listLine[0].trim();
				int pass = Integer.parseInt(listLine[1].trim());
				int fail = Integer.parseInt(listLine[2].trim());
				int skip = Integer.parseInt(listLine[3].trim());
				float total = pass+fail+skip;

				if(fail==0){
					if(i++%2==0)
						writerCr.append("<tr><td class=\"body\">"+moduleName+"</td><td style=\"text-align:center;\"><span class=\"pass\">"+ pass+"</span></td><td style=\"text-align:center;\"><span class=\"failed\">"+ fail+"</span></td><td style=\"text-align:center;\"><span class=\"skipped\">"+ skip+"</span></td><td style=\"text-align:center;\"><font class=\"pass\"> PASS </font></td></tr>");
					else
						writerCr.append("<tr bgcolor=\"#D7BDE2\"><td class=\"body\">"+moduleName+"</td><td style=\"text-align:center;\"><span class=\"pass\">"+ pass+"</span></td><td style=\"text-align:center;\"><span class=\"failed\">"+ fail+"</span></td><td style=\"text-align:center;\"><span class=\"skipped\">"+ skip+"</span></td><td style=\"text-align:center;\"><font class=\"pass\"> PASS </font></td></tr>");
				}/*else if (pass/total>=0.7) {
					if(i++%2==0)
						writerCr.append("<tr><td class=\"body\">"+moduleName+"</td><td style=\"text-align:center;\"><span class=\"pass\">"+ pass+"</span></td><td style=\"text-align:center;\"><span class=\"failed\">"+ fail+"</span></td><td style=\"text-align:center;\"><span class=\"skipped\">"+ skip+"</span></td><td style=\"text-align:center;\"><font class=\"skipped\"> PASS </font></td></tr>");
					else
						writerCr.append("<tr bgcolor=\"#D7BDE2\"><td class=\"body\">"+moduleName+"</td><td style=\"text-align:center;\"><span class=\"pass\">"+ pass+"</span></td><td style=\"text-align:center;\"><span class=\"failed\">"+ fail+"</span></td><td style=\"text-align:center;\"><span class=\"skipped\">"+ skip+"</span></td><td style=\"text-align:center;\"><font class=\"skipped\"> PASS </font></td></tr>");
				}*/else{
					if(i++%2==0)
						writerCr.append("<tr><td class=\"body\">"+moduleName+"</td><td style=\"text-align:center;\"><span class=\"pass\">"+ pass+"</span></td><td style=\"text-align:center;\"><span class=\"failed\">"+ fail+"</span></td><td style=\"text-align:center;\"><span class=\"skipped\">"+ skip+"</span></td><td style=\"text-align:center;\"><font class=\"failed\"> FAIL </font></td></tr>");
					else
						writerCr.append("<tr bgcolor=\"#D7BDE2\"><td class=\"body\">"+moduleName+"</td><td style=\"text-align:center;\"><span class=\"pass\">"+ pass+"</span></td><td style=\"text-align:center;\"><span class=\"failed\">"+ fail+"</span></td><td style=\"text-align:center;\"><span class=\"skipped\">"+ skip+"</span></td><td style=\"text-align:center;\"><font class=\"failed\"> FAIL </font></td></tr>");
				}

			}
			all.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		writerCr.print("</table>");


		writerCr.print("</body></html>");
		writerCr.close();


	}
}
