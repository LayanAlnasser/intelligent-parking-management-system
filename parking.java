package parking;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.math.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author layan
 */
public class Parking {

    /**
     * @param args the command line arguments
     */

   

    public static void main(String[] args) throws FileNotFoundException {
        
            
        String content;
        SinglyLinkedList<String> parking_infos = new SinglyLinkedList<>();

        try {
           int startread=(int) System.currentTimeMillis();
            try (Scanner sc = new Scanner(new File("input.txt"))) {
                while (sc.hasNextLine()) {
                    content = sc.nextLine();
                    parking_infos.addLast(content);
                }
              int endread=(int) System.currentTimeMillis();
               System.out.println("Reading time: " + (endread - startread) / 1000.0 );
       

            }
        } catch (FileNotFoundException fnf) {
         
       
            System.out.println("File not found");
        }
      //parking_infos.display();
      //create an output file
   
      String parking_info = parking_infos.removeFirst(); //getting the first element in the list
      int parking_fee;
      String output_content="";
      //read start time
      int startprocess=(int) System.currentTimeMillis();
      while(parking_info != null){
          parking_fee = calculateFee(parking_info);
          parking_info = parking_info.concat(" ");
          parking_info = parking_info.concat(String.valueOf(parking_fee));
          output_content+=parking_info;
           output_content+="\r\n";
         
          parking_info = parking_infos.removeFirst(); //getting the next element in the list
 
      }
     //read start tine
     //process start - end 
     int endprocess=(int)System.currentTimeMillis();
 
         System.out.println("Process time: " + (endprocess - startprocess) /1000.0);
         

       FileWriter output_file;
        try {
            //start time
            int startwrite=(int) System.currentTimeMillis();
            output_file = new FileWriter("output.txt");
             output_file.write(output_content);
            //end time
            int endwrite=(int) System.currentTimeMillis();
       
         System.out.println("Writing time: " + (endwrite - startwrite) / 1000.0);

      output_file.close();
      } catch (IOException ex) {
            Logger.getLogger(SinglyLinkedList.class.getName()).log(Level.SEVERE, null, ex);

        }
     
     
       
    } //End Main
   
        public static int getBasePrice(String Class) {
        if (Class.equals("1")) return 20;  // Student parking
        if (Class.equals("2")) return 40; // Staff & Faculty
        if (Class.equals("3")) return 70; // Higher Management
        return 100;  // Visitors
        } //end method
   
        public static int calculateFee(String parkingInfo) { //method return int number as requested in the output file format

        //Seperate the parking info into parking lot, class, day type ans time.
        int ind1st = parkingInfo.indexOf(";"); //index of first ;
        int ind2nd = parkingInfo.indexOf(";", ind1st+1); //index of second ;
        int ind3ed = parkingInfo.indexOf(";", ind2nd+1); //index of third ;
        int ind4th = parkingInfo.indexOf(";", ind3ed+1); //index of fourth ;
        String ParkingLot = parkingInfo.substring(0, ind1st);
        String Class = parkingInfo.substring(ind1st+2,ind2nd );
        String DayType = parkingInfo.substring(ind2nd+2,ind3ed );
        int Time = Integer.parseInt(parkingInfo.substring(ind3ed+2,ind4th)); //Convert the time to integer
        //get the base price
        int BaseFee = getBasePrice(Class);
        double FinalFee = BaseFee;
        // Applying the parking fees change rules
       
        //Rule#5: In case of Day 5 all parking lots are free for every one.
        if (DayType.equals("5") )
            return 0;
       
                //Rule#6: in case of a parking permit upgrade
        if (Class.equals("1")&& !ParkingLot.startsWith("P")){
            if(ParkingLot.equals("B102")||ParkingLot.equals("B104")) FinalFee = 70 + (70*0.15);
            else FinalFee = 40 + (40*0.15);
        }
        if (Class.equals("2")&& (ParkingLot.equals("B102")||ParkingLot.equals("B104")))
            FinalFee = 70 + (70*0.15);
       
       
        //Rule#7: in case of a parking permit downgrade
        if (Class.equals("3")&& !(ParkingLot.equals("B102")||ParkingLot.equals("B104"))) //if higher managment used any other parking lots
            FinalFee = FinalFee - (FinalFee*0.15);
        if (Class.equals("2")&& ParkingLot.startsWith("P")) //if staff/faculty used students lots
            FinalFee = FinalFee - (FinalFee*0.15);  
       
        //Rule#1: in case of Day 1 all parking lots are priced in increase of 100%
        if (DayType.equals("1")) FinalFee = FinalFee * 2.0;      
        //Rule#2: in case of Day 2 all parking lots are priced in increase of 25%        
        if (DayType.equals("2")) FinalFee = FinalFee + (FinalFee * 0.25);
        //Rule#3: in case of Day 3 all parking lots are priced at their original fees      
        //if (DayType.equals("3")) FinalFee = FinalFee;        
        //Rule#4: in case of Day 4 all parking lots are priced in decrease of 25%        
        if (DayType.equals("4")) FinalFee = FinalFee - (FinalFee * 0.25);
        //Rule#6: Dicounts based on Times
        if(DayType.equals("2")||DayType.equals("3")||DayType.equals("4")){
            if(Time >=6 && Time <8) FinalFee = FinalFee - (FinalFee*0.15); //6:00 - 8:00: a discount of 15% is applied
            if(Time >=8 && Time <10) FinalFee = FinalFee + (FinalFee*0.15); //8:00 - 10:00: an increase of 15% is applied
            if(Time >=12 && Time <13) FinalFee = FinalFee + (FinalFee*0.15);//12:00-13:00: an increase of 15% is applied
            if(Time >=15 && Time <18) FinalFee = FinalFee - (FinalFee*0.15);//15:00-18:00: a discound of 15% is applied
            if (Time>= 18) FinalFee = 0.0;//After 18:00: all parking lots are free  
        }
   
       
        //Include FAT
       
        FinalFee = FinalFee + (FinalFee*0.15);
       
        //Round to return integer number
        return Math.toIntExact(Math.round(FinalFee));
    } //End method
}
