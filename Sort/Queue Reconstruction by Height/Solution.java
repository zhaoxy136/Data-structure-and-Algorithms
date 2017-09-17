//Version 0:
    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0) return new int[0][0];
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] p, int[] q) {
                return p[0] == q[0] ? p[1] - q[1] : q[0] - p[0];
            }
        });
        for (int i = 1; i < people.length; i++) {
            if (people[i][1] < i) {
                int[] tmp = people[i];
                int j = i;
                while (j > tmp[1]) {
                    people[j] = people[j-1];
                    j--;
                }
                people[j] = tmp;
            }
        }
        return people;
    }
