import java.util.*;

public class Main {
  static int N;
  static List<Meeting> meetings = new ArrayList<>();
  static PriorityQueue<Integer> rooms = new PriorityQueue<>();

  static class Meeting {
    int start, end;

    public Meeting(int start, int end) {
      this.start = start;
      this.end = end;
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    N = Integer.parseInt(sc.nextLine());

    for(int i = 0; i < N; ++i) {
      String[] line = sc.nextLine().split(" ");
      meetings.add(new Meeting(Integer.parseInt(line[0]), Integer.parseInt(line[1])));
    }

    meetings.sort((a, b) -> a.start - b.start);

    rooms.offer(0);
    for(int i = 0; i < N; ++i) {
      Meeting meeting = meetings.get(i);

      int fastAvaliableRoom = rooms.poll();

      if(meeting.start < fastAvaliableRoom) {
        rooms.offer(fastAvaliableRoom);
      }
      rooms.offer(meeting.end);
    }

    System.out.println(rooms.size());
  }
}