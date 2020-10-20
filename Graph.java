import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
public class Graph extends JPanel {
	//Make sure can not add vertices from x=300 to x=500
	JRadioButton add_v ,add_e , rem_v,rem_e;
	JButton help;
	boolean add_vertices=false;
	boolean add_edges=false;
	boolean remove_vertices=false;
	boolean remove_edge=false;
	boolean test1=true;
	boolean test2=true;
	boolean test3=true;
	boolean test4=true;
	int x0,y0,x1,y1;
	ArrayList<Integer> x = new ArrayList<>();
	ArrayList<Integer> y = new ArrayList<>();
	ArrayList<Edge> A_Edges = new ArrayList<>();
	gfunctions g_obj=new gfunctions();
	public Graph() {
		add_v=new JRadioButton("Add Vertices");
		add_e=new JRadioButton("Add Edges");
		rem_v=new JRadioButton("Remove Vertices");
		rem_e=new JRadioButton("Remove Edges");
		help=new JButton("Help");
		myHandler handler=new myHandler();	
		ButtonGroup bg=new ButtonGroup();
		bg.add(add_v);
		bg.add(add_e);
		bg.add(rem_v);
		bg.add(rem_e);
		add(add_v);
		add(add_e);
		add(rem_v);
		add(rem_e);
		add(help);
		add_v.addActionListener(handler);
		add_e.addActionListener(handler);
		rem_v.addActionListener(handler);
		rem_e.addActionListener(handler);
		help.addActionListener(handler);
		addMouseListener(new Mouse());
	
	}
	public  class Mouse extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			 x0=e.getX();
			 y0=e.getY();
				if(add_vertices==true) {
				   x.add(x0);
				   y.add(y0);
				}
				if(add_edges==true) {
					if(test1==true && test2==true   ) {
						x1=x0;
						y1=y0;
						if(g_obj.vaild_ver(x1,y1)==true) {
							test1=false;
							test2=false;
						}
					}
					else if(test1=true && test2==false) {// Making sure that the user clicks on a vertex and when two vertex's are chosen make a edge.
						 if(g_obj.vaild_ver(x0,y0)==true) {// Gotta make sure I dont add Edge for same vertice
							 if(g_obj.Not_same_ver(x1, y1, x0, y0)==true) {
								 Edge edge=new Edge(x0,x1,y0,y1);
								 A_Edges.add(edge);
							 }
							 test2=true;	 
						 }
					}
					 test1=true;
				}
				if(remove_vertices==true) {
					gfunctions gob=new gfunctions();
					gob.myRem();
				}
				if(remove_edge==true) {
					g_obj.remove_edges();
				}	
		}
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.RED);
		for(int i=0;i<x.size();i++) {// Drawing Vertices.
			if(x.size()!=0 && y.size()!=0) {
			g.fillOval(x.get(i)-5,y.get(i)-5, 10, 10);
			}
		}
		g.setColor(Color.BLUE);
		for(int i=0;i<A_Edges.size();i++) {// Drawing Edges.
			g.drawLine(A_Edges.get(i).startvx2,A_Edges.get(i).endv2-4,
					A_Edges.get(i).startvx1, A_Edges.get(i).endv1-4);
		}
		this.repaint();	
	}
	public class myHandler implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			String g="Use the buttons to use GUI";
			if(event.getSource()==help)
				JOptionPane.showMessageDialog(null,g);
			if(event.getSource()==add_v) {
				add_vertices=true;
			}
			else add_vertices=false;
			if(event.getSource()==add_e) {
				add_edges=true;
			}
			else add_edges=false; 
			if(event.getSource()==rem_v) {
				remove_vertices=true;
			}
			else remove_vertices=false;
			
			if(event.getSource()==rem_e) {
				remove_edge=true;
			}
			else remove_edge=false;
		
}
	}
	public class gfunctions{
		public void myRem() {// Removes vertices by checking if the user clicked around the vertices. 
			if(x.size()!=0) {
				for(int i=0;i<x.size();i++) {
					if(x.get(i)==x0 && y.get(i)==y0){	
						x.set(i, -50); y.set(i, -50);}
					if(x.get(i)+2==x0 && y.get(i)+2==y0){
						x.set(i, -50); y.set(i, -50); }
					if(x.get(i)-2==x0 && y.get(i)-2==y0){
						x.set(i, -50); y.set(i, -50); }
					if(x.get(i)==x0 && y.get(i)-1==y0){
						x.set(i, -50); y.set(i, -50);}
					if(x.get(i)-1==x0 && y.get(i)+1==y0){
						x.set(i, -50); y.set(i, -50);}
					if(x.get(i)==x0 && y.get(i)+1==y0){
						x.set(i, -50); y.set(i, -50);}
					if(x.get(i)+1==x0 && y.get(i)==y0){
						x.set(i, -50); y.set(i, -50);}
					if(x.get(i)==x0 && y.get(i)+2==y0){
						x.set(i, -50); y.set(i, -50);}
					if(x.get(i)-1==x0 && y.get(i)+2==y0){
						x.set(i, -50); y.set(i, -50); }
					if(x.get(i)+1==x0 && y.get(i)+2==y0){
						x.set(i, -50); y.set(i, -50); }
					if(x.get(i)+1==x0 && y.get(i)+1==y0){
						x.set(i, -50); y.set(i, -50); }
					}
				}
			}
		public boolean vaild_ver(int xs,int ys) {// Checks if vertices are in created if so then can make edge.
			if(x.size()!=0) {
				for(int i=0;i<x.size();i++) {
					if( (xs>=x.get(i)-3 && xs<=x.get(i)+3) &&
							(ys>=y.get(i)-3 && ys<=y.get(i)+3)) return true;
				}
			}
			return false;
			}
		public boolean Not_same_ver(int xs,int ys,int x1s,int y1s) {// Checks if two vertices are same if so dont make a edge.		
					if(xs>=x1s+12 && xs<=x1s-12) return false;
					else if(ys>=y1s+12 && ys<=y1s-12) return false;
					return true;
			}
	
		public void remove_edges() {// Removes Edges but not perfectly.
			if(A_Edges.size()<=0) return ;
			else {
				for(int i=0;i<A_Edges.size();i++) {
					A_Edges.remove(i);
					}
				}	
			}
		}
}
