package modele;
class Edge
{
   int from;
   int to;
   int cost;
   Edge(int x, int y, int cost)
	 {
		this.from = x;
		this.to = y;
		this.cost = cost;
	 }
   public Edge(Edge e) {
	   	this.from = e.getFrom();
		this.to = e.getTo();
		this.cost = e.getCost();
	   	
	}
   
	public int getFrom() {
		return from;
	}
	public int getTo() {
		return to;
	}
	public int getCost() {
		return cost;
	}
   
}
