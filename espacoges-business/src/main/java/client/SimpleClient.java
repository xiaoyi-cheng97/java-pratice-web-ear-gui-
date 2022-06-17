package client;

import facade.exceptions.ApplicationException;
import facade.services.CheckOccupationService;
import facade.services.ProcessRequestsService;
import facade.services.ReservationRequestService;
import facade.startup.EspacoGes;
import facade.wrappers.Record;
import facade.wrappers.Request;

/**
 * A simple application client that uses all three services.
 *
 */
public class SimpleClient {
	
	/**
	 * An utility class should not have public constructors
	 */
	private SimpleClient() {
	}

    public static void main(String[] args) {

        EspacoGes app = new EspacoGes();
        try {
            app.run();
            
            // Access available services
            ReservationRequestService rrs = app.getReservationRequestService();
            ProcessRequestsService prs = app.getProcessRequestsService();
            CheckOccupationService cos = app.getCheckOccupationService(); 
           
            // 1
            System.out.println(rrs.makeReservation());
            rrs.giveId(3);
            rrs.chooseSport("Futsal");
            rrs.createReservation("pequenas curvas", "01/07/2019 09:00", "01/07/2019 12:00", 10);
            
            // 2
            System.out.println(rrs.makeReservation());
            rrs.giveId(4);
            rrs.chooseSport("Futsal");
            rrs.createReservation("torneio dos leões", "05/07/2019 14:00", "07/07/2019 19:00", 20);
            
            // 3
            System.out.println(rrs.makeReservation());
            rrs.giveId(1);
            rrs.chooseSport("Futsal");
            rrs.createReservation("rebola rapido", "01/07/2019 11:00", "01/07/2019 13:00", 12);
          
            // 4
            System.out.println(rrs.makeReservation());
            rrs.giveId(2);
            rrs.chooseSport("Futsal");
            rrs.createReservation("torneio das águias", "01/07/2019 14:00", "03/07/2019 13:00", 20);
          
            // space names
            String space1 = "Estádio de Honra - Campo de Jogo";
            String space2 = "Pavilhão nº1 - Campo Central";
            // 5
            System.out.println(prs.startProcessing());
            for (Request r : prs.filterRequests("Futsal", "Club")) {
            	System.out.println(r.getDescription());
            }
			prs.chooseRequest(3, space1);
            
            System.out.println(prs.startProcessing());
            for (Request r : prs.filterRequests("Futsal", "Club")) {
            	System.out.println(r.getDescription());
            }
			prs.chooseRequest(7, space2);
            
            // 6
            System.out.println(space1);
            for (Record rec : cos.checkUseOfSpace(space1, "01/07/2019 00:00" , "08/07/2019 00:00")) {
            	System.out.println(rec.getPeriod());
            }
            
            // 7
            System.out.println(space2);
            for (Record rec : cos.checkUseOfSpace(space2, "01/07/2019 00:00" , "08/07/2019 00:00")) {
            	System.out.println(rec.getPeriod());
            }
            
            // 8
            System.out.println(prs.startProcessing());
            for (Request r : prs.filterRequests("Futsal", "Individual")) {
            	System.out.println(r.getDescription());
            }
            prs.chooseRequest(1, space2);
            
            System.out.println(prs.startProcessing());
            for (Request r : prs.filterRequests("Futsal", "Individual")) {
            	System.out.println(r.getDescription());
            }
            
            try {
            	prs.chooseRequest(5, space2);
            } catch (ApplicationException e) {
            	System.out.println("Error: " + e.getMessage());
            	if (e.getCause() != null) {
            		System.out.println("Cause: ");
            	}
            	e.printStackTrace();
            }
            
            System.out.println(prs.startProcessing());
            for (Request r : prs.filterRequests("Futsal", "Individual")) {
            	System.out.println(r.getDescription());
            }
            prs.chooseRequest(5, space1);
            
            // 10
            System.out.println(space1);
            for (Record rec : cos.checkUseOfSpace(space1, "01/07/2019 00:00" , "08/07/2019 00:00")) {
            	System.out.println(rec.getDate() + " " + rec.getStartTime() + " - " + rec.getEndTime());
            }
            
            // 11
            System.out.println(space2);
            for (Record rec : cos.checkUseOfSpace(space2, "01/07/2019 00:00" , "08/07/2019 00:00")) {
            	System.out.println(rec.getDate() + " " + rec.getStartTime() + " - " + rec.getEndTime());
            }
            
            
            app.stopRun();
        } catch (ApplicationException e) {
        	System.out.println("Error: " + e.getMessage());
        	if (e.getCause() != null) {
        		System.out.println("Cause: ");
        	}
        	e.printStackTrace();
        }
    }
}