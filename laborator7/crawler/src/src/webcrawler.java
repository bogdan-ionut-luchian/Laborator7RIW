package src;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class webcrawler {
	private static Queue<String> q = new LinkedList<String>();
	private static Queue<String> qChecked = new LinkedList<String>();
	public static String text;
	public static String path = "D:\\Facultate\\AN 4 SEM 2\\laborator7\\crawler\\http\\";

	public static void HTMLParsing() 
	{
		
	}
	public static void main(String[] args) throws IOException {
		Document doc;
		try {
			String url="http://riweb.tibeica.com/";
			doc = Jsoup.connect(url).get();
			Elements links = doc.select("a[href]");
			for(Element l: links)
			{
				String dir=l.baseUri();
				File files=new File(dir);
				if (!files.exists()) 
				{
					if (files.mkdirs()) 
					{
						System.out.println("Directoarele au fost create!");
					} 
					else 
					{
						System.out.println("Eroare!");
					}
				}
			}
			for(Element l: links)
			{
				String ll=l.attr("abs:href");
				q.add(ll);
				System.out.println(ll);
			}
			FileWriter fw = new FileWriter(url+"/index.html");
			FileWriter fwx = new FileWriter(path+"/index.html");
			BufferedWriter bw = new BufferedWriter(fw);
			BufferedWriter bwx=new BufferedWriter(fwx);
			bw.write(doc.body().toString());
			bwx.write(doc.body().toString());
			
			bw.close();
			bwx.close();
			while (!q.isEmpty()) 
			{
				String link = q.poll();
				if (!qChecked.contains(link)) {
					qChecked.add(link);
				    doc = Jsoup.connect(link).get();
					FileWriter fw1 = new FileWriter(link);
					BufferedWriter bw1 = new BufferedWriter(fw1);
					bw1.write(doc.body().toString());
					bw1.close();
				}
			}
		}catch (Exception e) {
		}
	}

}
