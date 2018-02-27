package modele;
class Test
{
   static boolean visite[];
   public static void dfs(Graph g, int u)
	 {
		visite[u] = true;
		System.out.println("Je visite " + u);
		for (Edge e: g.next(u))
		  if (!visite[e.to])
			dfs(g,e.to);
	 }

   public static void testHeap()
	 {
		// Crée ue file de priorité contenant les entiers de 0 à 9, tous avec priorité +infty
		Heap h = new Heap(10);
		h.decreaseKey(3,1664);
		h.decreaseKey(4,5);
		h.decreaseKey(3,8);
		h.decreaseKey(2,3);
		// A ce moment, la priorité des différents éléments est:
		// 2 -> 3
		// 3 -> 8
		// 4 -> 5
		// tout le reste -> +infini
		int x=  h.pop();
		System.out.println("On a enlevé "+x+" de la file, dont la priorité était " + h.priority(x));
		x=  h.pop();
		System.out.println("On a enlevé "+x+" de la file, dont la priorité était " + h.priority(x));
		x=  h.pop();
		System.out.println("On a enlevé "+x+" de la file, dont la priorité était " + h.priority(x));
		// La file contient maintenant uniquement les éléments 0,1,5,6,7,8,9 avec priorité +infini
	 }
   
   public static void testGraph()
	 {
		int n = 5;
		int i,j;
		Graph g = new Graph(n*n+2);
		
		for (i = 0; i < n-1; i++)
		  for (j = 0; j < n ; j++)
			g.addEdge(new Edge(n*i+j, n*(i+1)+j, 1664 - (i+j)));

		for (j = 0; j < n ; j++)		  
		  g.addEdge(new Edge(n*(n-1)+j, n*n, 666));
		
		for (j = 0; j < n ; j++)					
		  g.addEdge(new Edge(n*n+1, j, 0));
		
		g.addEdge(new Edge(13,17,1337));
		g.writeFile("test.dot");
		// dfs à partir du sommet 3
		visite = new boolean[n*n+2];
		dfs(g, 3);
	 }
   
   public static void testGraph2()
	 {
	   
		Graph g;
		int[][] t = {{  8, 2,1,15},
					 { 13, 2,1,10},
					 {140,52,5,25}};
		g=tograph2(t);
		g.writeFile("test.dot");
	 }
   
   static Graph tograph2(int[][] itr){
		int nbSommet = (itr.length*itr[0].length)*2+2;
		int cmp=1;
		Graph g = new Graph(nbSommet);
		for (int i =0;i<itr[0].length;i++){
			g.addEdge(new Edge(0, cmp, 0));
			g.addEdge(new Edge(cmp, cmp+itr[0].length, 0));
			g.addEdge(new Edge((nbSommet-1)-(itr[0].length-cmp)-1, nbSommet-1, itr[itr.length-1][cmp-1]));
			cmp++;
		}
		cmp=1+itr[0].length;
		for (int i =0;i<itr.length-1;i++){
			for (int j=0;j<itr[0].length;j++)
			{
			g.addEdge(new Edge(cmp, cmp+itr[0].length, itr[i][j]));
			g.addEdge(new Edge(cmp+itr[0].length, cmp+(itr[0].length*2), 0));
			if (j>0)
				g.addEdge(new Edge(cmp, cmp+itr[0].length-1, itr[i][j]));
			if (j<itr[0].length-1)
				g.addEdge(new Edge(cmp, cmp+itr[0].length+1, itr[i][j]));
			cmp++;
			}
			cmp=cmp+itr[0].length;
		}		
		return g;
	}
   
   public static void main(String[] args)
	 {
	   Test t=new Test();
		testGraph2();
	 }
}
