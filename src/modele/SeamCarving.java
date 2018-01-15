package modele;
import java.io.*;
import java.util.*;
public class SeamCarving
{
	
   public static int[][] readpgm(String fn)
	 {		
	   File fi =  new File(fn);
		if (fi.exists())  System.out.print("found ");
		       else System.out.print("not found");
        try {
           
            File fa = new File (fn);
            FileReader f = new FileReader (fa);
            BufferedReader d = new BufferedReader(f);
            String magic = d.readLine();
            System.out.println("Magic"+magic);
            String line = d.readLine();
		   while (line.startsWith("#")) {
			  line = d.readLine();
		   }
		   Scanner s = new Scanner(line);
		   int width = s.nextInt();
		   int height = s.nextInt();		   
		   line = d.readLine();
		   s = new Scanner(line);
		   int maxVal = s.nextInt();
		   int[][] im = new int[height][width];
		   s = new Scanner(d);
		   int count = 0;
		   while (count < height*width) {
			  im[count / width][count % width] = s.nextInt();
			  count++;
		   }
		   return im;
        }
		
        catch(Throwable t) {
            t.printStackTrace(System.err) ;
            return null;
        }
    }	
   
   public static void writepgm(int[][] image, String filename){
	   try {
		   BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filename+".pgm")));
		   
		   writer.write("P2\n");
		   writer.write(image[0].length+" "+image.length+"\n");
		   writer.write("255\n");
		   for (int i=0; i<image.length; i++){
			   for(int j = 0; j<image[0].length;j++){
				   writer.write(image[i][j]+"\n");				   
			   }
			   writer.write("");
		   }
		   writer.close();
		   }
		   catch (IOException e)
		   {
		   e.printStackTrace();
		   }
   }
   
}
