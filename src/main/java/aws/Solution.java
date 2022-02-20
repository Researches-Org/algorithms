package aws;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

// Time complexity: O(r * c).
// Space complexity: O(r * c).
// Uses a breath first search with a queue of servers (each cell that contains a server, which is 1).
// At first, the queue is initialized and the number of cells where a file is missing is counted.
// If no cell is missing a file (the count is zero) just returns zero.
// Otherwise, calculate the number of hours by removing each server from the queue and navigating
// to the allowed directions checking if they are empty, in case of empty, decrease the counter of
// files, checks if is zero, in case it is zero, return the number of hours, in case not, update the cell
// and add it to the queue of servers.

public class Solution {


    private static final int SERVER = 1;
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int minimumHours(int rows, int columns, List<List<Integer>> grid) {
        int files = 0;

        Queue<Cell> servers = new ArrayDeque<>();

        for (int r = 0; r < grid.size(); r++) {
            for (int c = 0; c < grid.get(0).size(); c++) {
                if (grid.get(r).get(c) == SERVER) {
                    servers.add(new Cell(r, c));
                } else {
                    files++;
                }
            }
        }

        if (files == 0) return 0;

        for (int hours = 1; !servers.isEmpty(); hours++) {
            for (int sz = servers.size(); sz > 0; sz--) {
                Cell server = servers.poll();

                for (int[] dir : DIRECTIONS) {
                    int r = server.r + dir[0];
                    int c = server.c + dir[1];

                    if (isEmpty(grid, r, c)) {
                        files--;
                        if (files == 0) return hours;
                        grid.get(r).set(c, SERVER);
                        servers.add(new Cell(r, c));
                    }
                }
            }
        }
        return -1;
    }

    private boolean isEmpty(List<List<Integer>> grid, int r, int c) {
        return r >= 0 && r < grid.size() &&c >= 0 && c < grid.get(0).size() && grid.get(r).get(c) != SERVER;
    }

    private static class Cell {
        int r, c;

        Cell(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();

        List<List<Integer>> grid = new ArrayList<>();
        grid.add(Arrays.asList(new Integer[]{1, 0, 0, 0, 0}));
        grid.add(Arrays.asList(new Integer[]{0, 1, 0, 0, 0}));
        grid.add(Arrays.asList(new Integer[]{0, 0, 1, 0, 0}));
        grid.add(Arrays.asList(new Integer[]{0, 0, 0, 1, 0}));
        grid.add(Arrays.asList(new Integer[]{0, 0, 0, 0, 1}));

        System.out.println(s.minimumHours(5, 5, grid));


        grid = new ArrayList<>();
        grid.add(Arrays.asList(new Integer[]{0, 0, 1, 0, 0, 0}));
        grid.add(Arrays.asList(new Integer[]{0, 0, 0, 0, 0, 0}));
        grid.add(Arrays.asList(new Integer[]{0, 0, 0, 0, 0, 1}));
        grid.add(Arrays.asList(new Integer[]{0, 0, 0, 0, 0, 0}));
        grid.add(Arrays.asList(new Integer[]{0, 1, 0, 0, 0, 0}));

        System.out.println(s.minimumHours(5, 6, grid));
    }

}
