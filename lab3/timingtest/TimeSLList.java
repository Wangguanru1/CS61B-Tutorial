package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        AList<Integer> aList=new AList<>();
        addDoubleNum(aList,1000,8);
        AList<Integer> opCounts =new AList<>();
        int m=aList.size();
        for(int i=0;i<m;i++){
            opCounts.addLast(10000);
        }
        AList<Double> times=new AList<>();
        for(int i=0;i<m;i++){
            int n=aList.get(i);
            SLList<Integer> temp=new SLList<>();
            for(int j=0;j<n;j++){
                temp.addLast(1);
            }
            Stopwatch sw = new Stopwatch();
            for(int j=0;j<10000;j++){
                temp.addLast(1);
            }
            double timeInSeconds = sw.elapsedTime();
            times.addLast(timeInSeconds);
        }
        printTimingTable(aList,times,opCounts);
    }
    private static void addDoubleNum(AList<Integer> aList,int num,int times){
        for(int i=0;i<times;i++){
            aList.addLast(num);
            num*=2;
        }
    }
}
