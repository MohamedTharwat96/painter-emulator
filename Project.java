//import needed files
import java.awt.*;
import java.util.*;
import java.awt.event.*; 
import java.applet.Applet;

//create applet class 
public class Project extends Applet{
	
	//create class for shapes with constructor and getters and setters
	class Shape{
		
		//declare variables
		protected int px1;
		protected int py1;
		protected int px2;
		protected int py2;
		protected int type;
		protected Color color;
		protected boolean issolid;
		
		//define setters and getters
		public void setpx1(int px1){
		this.px1 = px1;
		}
		public void setpy1(int py1){
		this.py1 = py1;
		}
		public void setpx2(int px2){
		this.px2 = px2;
		}
		public void setpy2(int py2){
		this.py2 = py2;
		}
		public int getpx1(){
			return px1;
		}
		public int getpy1(){
			return py1;
		}
		public int getpx2(){
			return px2;
		}
		public int getpy2(){
			return py2;
		}
		
		//define constructor
		public Shape(int px1, int py1, int px2, int py2, int type ,Color color, boolean issolid){
			this.px1 = px1;
			this.py1 = py1;
			this.px2 = px2;
			this.py2 = py2;
			this.type = type;
			this.color = color;
			this.issolid = issolid;
		}	
	public Shape(int px1, int py1, int px2, int py2, int type ,Color color){
			this.px1 = px1;
			this.py1 = py1;
			this.px2 = px2;
			this.py2 = py2;
			this.type = type;
			this.color = color;
		}		
	}
	//define class line
	class Line extends Shape{
		//override shape constructor
		public Line(int x1, int y1, int x2, int y2, int type ,Color color, boolean issolid){
			super(x1, y1, x2, y2, type, color, issolid);
		}
	}
	//define class Rectangle
	class Rectangle extends Shape{
		//override shape constructor
		public Rectangle(int x1, int y1, int x2, int y2, int type ,Color color, boolean issolid){
			super(x1, y1, x2, y2, type, color, issolid);
		}
	}
	//define class Oval
	class Oval extends Shape{
		//override shape constructor
		public Oval(int x1, int y1, int x2, int y2, int type ,Color color, boolean issolid){
			super(x1, y1, x2, y2, type, color, issolid);
		}
	}
	//define class sketch line
	class SketchLine extends Shape{
		//override shape constructor
		public SketchLine(int x1, int y1, int x2, int y2, int type, Color color){
			super(x1, y1, x2, y2, type, color);
		}
	}
	
	
	// declare global variables
	protected int x1;
	protected int y1;
	protected int x2;
	protected int y2;
	protected int width;
	protected int height;
	protected int counter = 0;
	protected int counters = 0;
	protected boolean solid_flag = false;
	protected Color current_color = Color.black;
	protected boolean isdragging = false;
	protected int eraserwidth = 10;
	protected int eraserheight = 10;
	
	//create array list
	ArrayList<Shape> shapes_array = new ArrayList<>();
	
	
	//ArrayList<SketchLine> arr2 = new ArrayList<>();
	
	
	//define mode map
	public static final int line_mode=1;
	public static final int rectangle_mode=2;
	public static final int oval_mode=3;
	public static final int sketch_mode=4;
	public static final int erase_mode=5;
	int current_mode=0;
	
	//declare buttons
	Button red;
	Button green;
	Button blue;
	Button line;
	Button rectangle;
	Button oval;
	Button sketch;
	Button eraser;
	Button clear;
	Checkbox solid;
	
	//init function initiates buttons and listeners
	public void init(){
		
		//make button objects
		red = new Button("red");
		green = new Button("green");
		blue = new Button("blue");
		line = new Button("line");
		rectangle = new Button("rectangle");
		oval = new Button("oval");
		solid = new Checkbox("solid");
		sketch = new Button("sketch");
		eraser = new Button("eraser");
		clear = new Button("clear");
		
		//button listeners
		line.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			current_mode = line_mode;
		}
		});
		rectangle.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			current_mode = rectangle_mode;
		}
		});
		oval.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			current_mode = oval_mode;
		}
		});
		sketch.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			current_mode = sketch_mode;
		}
		});
		eraser.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			current_mode = erase_mode;
		}
		});
		red.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			current_color = Color.red;
		}
		});
		blue.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			current_color = Color.blue;
		}
		});
		green.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			current_color = Color.green;
		}
		});
		solid.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
			if (solid_flag == false){
				solid_flag = true;
			}
			else{
				solid_flag = false;
			}
		}
		});
		clear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			shapes_array.clear();
			repaint();
			current_mode = 0;
			current_color = Color.black;
		}
		});
		
		//adding buttons
		add(red);
		add(green);
		add(blue);
		add(line);
		add(rectangle);
		add(oval);
		add(sketch);
		add(eraser);
		add(clear);
		add(solid);
		
		//mouse listeners		
		this.addMouseListener(new MouseAdapter(){
			//press record x1, y1
			public void mousePressed(MouseEvent e){
				x1 = e.getX();
				y1 = e.getY();
			}
			//relaease saves and appends painted object to the array list
			public void mouseReleased(MouseEvent e){
				if(isdragging == true){
					switch(current_mode){
						case line_mode:
							shapes_array.add(new Line(x1, y1, x2, y2, current_mode, current_color, solid_flag));
							counter++;
						break;
						case rectangle_mode:
							if (width<0){
								x1+=width;
								width=Math.abs(width);
							}
							if(height<0){
								y1+=height;
								height=Math.abs(height);
							}
						shapes_array.add(new Rectangle(x1, y1, width, height, current_mode, current_color, solid_flag));
						counter++;
						break;
						case oval_mode:
							if (width<0){
								x1+=width;
								width=Math.abs(width);
							}
							if(height<0){
								y1+=height;
								height=Math.abs(height);
							}
						shapes_array.add(new Oval(x1, y1, width, height, current_mode, current_color, solid_flag));
						counter++;
						break;
					}
				}
				isdragging = false;
			}
		});
		this.addMouseMotionListener(new MouseAdapter(){
			//drag record next points and repaint
			public void mouseDragged(MouseEvent e){
				isdragging = true;
				switch(current_mode){
					case rectangle_mode:
						width = e.getX()-x1;
						height = e.getY()-y1;
					break;
					case oval_mode:
						width = e.getX()-x1;
						height = e.getY()-y1;
					break;
					default:
						x2 = e.getX();
						y2 = e.getY();
				}
				repaint();
			}
		});
	}
	
	//paint function to paint each shape
	public void paint(Graphics g){
		
		//setting color
		g.setColor(current_color);
		
		//paint current shape
		switch(current_mode){
			case line_mode:
				g.drawLine(x1, y1, x2, y2);
			break;
			case rectangle_mode:
			if (solid_flag == false){
				if(width<0 && height<0){
					g.drawRect(x1+width, y1+height, Math.abs(width), Math.abs(height));
				}
				if(height<0 && width>=0){
					g.drawRect(x1, y1+height, width, Math.abs(height));
				}
				if(width<0 && height>=0){
					g.drawRect(x1+width, y1, Math.abs(width), height);
				}
				if(width>=0 && height>=0){
					g.drawRect(x1, y1, width, height);
				}
			}
			else if(solid_flag == true){
				if(width<0 && height<0){
					g.fillRect(x1+width, y1+height, Math.abs(width), Math.abs(height));
				}
				if(height<0 && width>=0){
					g.fillRect(x1, y1+height, width, Math.abs(height));
				}
				if(width<0 && height>=0){
					g.fillRect(x1+width, y1, Math.abs(width), height);
				}
				if(width>=0 && height>=0){
					g.fillRect(x1, y1, width, height);
				}
			}
			break;
			case oval_mode:
				if (solid_flag == false){
					if(width<0 && height<0){
						g.drawOval(x1+width, y1+height, Math.abs(width), Math.abs(height));
					}
					if(height<0 && width>=0){
						g.drawOval(x1, y1+height, width, Math.abs(height));
					}
					if(width<0 && height>=0){
						g.drawOval(x1+width, y1, Math.abs(width), height);
					}
					if(width>=0 && height>=0){
						g.drawOval(x1, y1, width, height);
					}
				}
				else if(solid_flag == true){
					if(width<0 && height<0){
						g.fillOval(x1+width, y1+height, Math.abs(width), Math.abs(height));
					}
					if(height<0 && width>=0){
						g.fillOval(x1, y1+height, width, Math.abs(height));
					}
					if(width<0 && height>=0){
						g.fillOval(x1+width, y1, Math.abs(width), height);
					}
					if(width>=0 && height>=0){
						g.fillOval(x1, y1, width, height);
					}
			}
			break;
			case sketch_mode:
				g.drawLine(x1, y1, x2, y2);
				x1 = x2;
				y1 = y2;
				shapes_array.add(new SketchLine(x1, y1, x2, y2, current_mode, current_color));
				counters++;
			break;
			case erase_mode:
				g.drawRect(x2, y2,eraserwidth ,eraserheight);
				shapes_array.add(new Rectangle(x2, y2, width, height, current_mode, getBackground(), true));
				counters++;
			break;
		}
		
		//paint old shapes 
		for(int i=0; i<shapes_array.size(); i++){
			g.setColor(shapes_array.get(i).color);
			switch(shapes_array.get(i).type){
				case line_mode:
					g.drawLine(shapes_array.get(i).px1, shapes_array.get(i).py1, shapes_array.get(i).px2, shapes_array.get(i).py2);
				break;
				case rectangle_mode:
					if(shapes_array.get(i).issolid == false){
						g.drawRect(shapes_array.get(i).px1, shapes_array.get(i).py1, shapes_array.get(i).px2, shapes_array.get(i).py2);
					}
					else if(shapes_array.get(i).issolid == true){
						g.fillRect(shapes_array.get(i).px1, shapes_array.get(i).py1, shapes_array.get(i).px2, shapes_array.get(i).py2);
					}
				break;	
				case oval_mode:
					if(shapes_array.get(i).issolid == false){
						g.drawOval(shapes_array.get(i).px1, shapes_array.get(i).py1, shapes_array.get(i).px2, shapes_array.get(i).py2);
					}
					else if(shapes_array.get(i).issolid == true){
						g.fillOval(shapes_array.get(i).px1, shapes_array.get(i).py1, shapes_array.get(i).px2, shapes_array.get(i).py2);
					}
				break;
				case sketch_mode:
					g.drawLine(shapes_array.get(i).px1, shapes_array.get(i).py1,shapes_array.get(i).px2, shapes_array.get(i).py2);
				break;
				case erase_mode:
					g.drawRect(shapes_array.get(i).px1, shapes_array.get(i).py1,eraserwidth ,eraserheight);
				break;
			}
		}
	}
}