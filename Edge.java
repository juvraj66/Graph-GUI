
public class Edge {
	int startvx1;
    int startvx2;
    int endv1;
    int endv2;
   
    Edge(int x1, int x2,int y1,int y2){
    	startvx1=x1;
    	startvx2=x2;
    	endv1=y1;
    	endv2=y2; 	
    }
    void printA() {
    	System.out.println("x1 "+startvx1);
    	System.out.println("y1 "+endv1);
    	System.out.println("x2 "+startvx2);
    	System.out.println("y2 "+endv2);
    }
}
