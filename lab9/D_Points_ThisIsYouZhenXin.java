package lab9;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class D_Points_ThisIsYouZhenXin {
	public static void main(String[] args) throws IOException {
		Reader in = new Reader();
		PrintWriter out = new PrintWriter(System.out);
//
//		int size = in.nextInt();
//		while (size > 0){

			int n = in.nextInt();
			int m = in.nextInt();

			// init graph
			var g = new ArrayList<ArrayList<Edge>>(n*m);
			long[][] c = new long[n][m];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					c[i][j] = in.nextLong();
					g.add(new ArrayList<>());
				}
			}
			int index = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++, index++) {
					// right
					if (j != m-1) {
						long tmp = c[i][j] * c[i][j+1];
						g.get(index).add(new Edge(index+1, tmp));
						g.get(index+1).add(new Edge(index, tmp));
					}
					// down
					if (i != n-1) {
						long tmp = c[i][j] * c[i+1][j];
						g.get(index).add(new Edge(index+m, tmp));
						g.get(index+m).add(new Edge(index, tmp));
					}
				}
			}

			// Max-st
			long res = 0;
			boolean[] isInS = new boolean[n*m];
			var cmp = new Comparator<Edge>() {
				@Override
				public int compare(Edge o1, Edge o2) {
					return Long.compare(o2.w, o1.w);
				}
			};
			PriorityQueue<Edge> pq = new PriorityQueue<>(cmp);
			isInS[0] = true;
			pq.addAll(g.get(0));

			while (!pq.isEmpty()) {
				Edge x = pq.poll();
				if (isInS[x.v]) { continue; }
				res += x.w;
				isInS[x.v] = true;
				for (Edge e : g.get(x.v)) {
					if (!isInS[e.v]) {
						pq.add(e);
					}
				}
			}

			out.println(res);


////			size--;
//		}


		out.close();
	}

	static class Edge {
		int v;
		long w;
		Edge(int v, long w) {
			this.v = v;
			this.w = w;
		}
		@Override
		public String toString() {
			return "(" + v + "," + w + ")";
		}
	}

	public static String toString(HashMap<Integer, ArrayList<Integer>> m) {
		var sbd = new StringBuilder();
		for (int i = 0; i < m.size(); i++) {
			sbd.append(i).append(": ");
			sbd.append(m.get(i)).append("\n");
		}

		return sbd.toString();
	}

	static class Reader {
		final private int BUFFER_SIZE = 1 << 16;
		private DataInputStream din;
		private byte[] buffer;
		private int bufferPointer, bytesRead;

		public Reader()
		{
			din = new DataInputStream(System.in);
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public Reader(String file_name) throws IOException
		{
			din = new DataInputStream(new FileInputStream(file_name));
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public String readLine() throws IOException
		{
			byte[] buf = new byte[64]; // line length
			int cnt = 0, c;
			while ((c = read()) != -1)
			{
				if (c == '\n')
					break;
				buf[cnt++] = (byte) c;
			}
			return new String(buf, 0, cnt);
		}

		public int nextInt() throws IOException
		{
			int ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do
			{
				ret = ret * 10 + c - '0';
			}  while ((c = read()) >= '0' && c <= '9');

			if (neg)
				return -ret;
			return ret;
		}

		public long nextLong() throws IOException
		{
			long ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			}
			while ((c = read()) >= '0' && c <= '9');
			if (neg)
				return -ret;
			return ret;
		}

		public double nextDouble() throws IOException
		{
			double ret = 0, div = 1;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();

			do {
				ret = ret * 10 + c - '0';
			}
			while ((c = read()) >= '0' && c <= '9');

			if (c == '.')
			{
				while ((c = read()) >= '0' && c <= '9')
				{
					ret += (c - '0') / (div *= 10);
				}
			}

			if (neg)
				return -ret;
			return ret;
		}

		private void fillBuffer() throws IOException
		{
			bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
			if (bytesRead == -1)
				buffer[0] = -1;
		}

		private byte read() throws IOException
		{
			if (bufferPointer == bytesRead)
				fillBuffer();
			return buffer[bufferPointer++];
		}

		public void close() throws IOException
		{
			if (din == null)
				return;
			din.close();
		}
	}
}
