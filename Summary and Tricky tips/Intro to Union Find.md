# Introduction to Union Find(并查集）

## 涉及题目
 > [Friend Circles](https://leetcode.com/problems/friend-circles/description/)  
 > [Graph Valid Tree](https://leetcode.com/problems/graph-valid-tree/description/)  
 > [Number of Islands II](https://leetcode.com/problems/number-of-islands-ii/description/)  
 
 
## 解题思路
此算法主要用于解决`动态连通性`的问题
其与DFS的主要区别可由以下场景看出：  
+ 给出两个节点，判断它们是否连通，如果连通，不需要给出具体的路径(Union-Find)
+ 给出两个节点，判断它们是否连通，如果连通，给出具体的路径(DFS)

对于Union-Find，我们通过给每个节点设定一个id，用array来记录；另外在用count来记录当前连通器的数量。  

对于该数据结构主要的操作有：
 + int `getCount()`
 + void `union(int p, int q)`
 + int `find(int p)`
 + boolean `isConnect(int p, int q)`

一、我们的初步实现为：

    public class UF {
      int count;   //连通分量数
      int[] id;    //每个数所属的连通分量

      public UF(int N) {   //初始化时，N个点有N个分量
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
      }
      public int getCount(){
        return count;
      }
    
      public int find(int x){
        return id[x];
      }
      public void union(int p,int q){
        int pID=find(p);
        int qID=find(q);
        for(int i=0;i<id.length;i++){
            if(find(i)==pID){
                id[i]=qID;
            }
        }
        count--;    //记得每进行一次连接，分量数减“1”
      }
      
      //判断p,q是否连接，即是否属于同一个分量
      public boolean connected(int p,int q){
        return find(p)==find(q);
      }
    }
上述算法中find复杂度为O(1), union复杂度为O(n). 故称作**quick-find**

二、union操作的复杂度过高，于是我们引入树的概念，当做union时，即为将两个树合并的过程：先找到两个节点所在树的根节点，然后合并。对union和find做以下修改：

      public int find(int x){
        //若不是根节点，则继续向上找
        while (x != id[x]) {
          x = id[x];
        }
        return x;
      }
      
      public void union(int p,int q){
        int pRoot=find(p);
        int qRoot=find(q);
        if (pRoot == qRoot) return;
        id[pRoot] = qRoot;
        count--;
      }
    
由于这种实现提高了union的效率，故被称为**quick-union**算法。find()的复杂度为O(lgn) ~ O(n). union()的复杂度也是O(lgn) ~ O(n),即树的高度。

三、很容易注意到，当上述结构中树极度不平衡时，union和find的复杂度都是O(n),于是我们希望每次union时将树尽可能的平衡。则需要根据两者的大小来判断：总是将size较小的树并为size较大树的子树。

    public class UF {
      int count;   //连通分量数
      int[] id;    //每个数所属的连通分量
      int[] size;  //每个节点所属分支的规模
      
      public UF(int N) {   //初始化时，N个点有N个分量
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            size[i] = 1;
        }
      }
      public int getCount(){
        return count;
      }
      
      public int find(int x){
        //若不是根节点，则继续向上找
        while (x != id[x]) {
          x = id[x];
        }
        return x;
      }
      
      public void union(int p,int q){
        int pRoot=find(p);
        int qRoot=find(q);
        if (pRoot == qRoot) return;
        if (size[pRoot] > size[qRoot]) {
           id[qRoot] = pRoot;
           size[pRoot] += size[qRoot];
        } else {
           id[pRoot] = qRoot;
           size[qRoot] += size[pRoot];
        }
        count--;
      }
      
      //判断p,q是否连接，即是否属于同一个分量
      public boolean connected(int p,int q){
        return find(p)==find(q);
      }
    }

通过size数组决定如何对两棵树进行合并之后，最后得到的树的高度大幅度减小了。find和union的复杂度都稳定在O(lgn)级别。称为**weighted UF 算法**

四、注意到通过上述算法，我们数据的整体结构如下图表示：
![](https://github.com/zhaoxy136/LeetCode/blob/master/Summary%20and%20Tricky%20tips/assets/Union%20Find.png)
换句话说：树的结构越**扁平化**，复杂度就越低。那么我们可以通过**压缩路径**的方法进一步“压扁”这棵树。  
具体做法是：在进行find的时候，将节点的父节点指向该节点的爷爷节点。这一点很巧妙，相当于在寻找根节点的同时，对路径进行了压缩，使整个树结构扁平化。  
代码实现上也只是对find方法做了微调，加了一行代码。

    public int find(int x){
      while (x != id[x]) {
        id[x] = id[id[x]];
        x = id[x];
      }
      return x;
    }

至此，find和union的复杂度基本可以达到接近O(1)的复杂度。

### Friend Circles
本题实则为一无向图，点与点之间相连，求出共有几个连通分量。属于典型的union-find问题。按照模型先构建UF类。  
题目的输入为adjacency matrix，且矩阵是对称的。我们只需要遍历矩阵i从0~n，j从i+1~n（因为，对角线必然联通，另一侧又可由对称处理）

    public int findCircleNum(int[][] M) {
        if (M == null || M.length == 0) return 0;
        UF uf = new UF(M.length);
        for (int i = 0; i < M.length; i++) {
            for (int j = i + 1; j < M[0].length; j++) {
                if (M[i][j] == 1)
                    uf.union(i, j);
            }
        }
        return uf.getCount();
    }

### Graph Valid Tree
题目要求判断给定的无向图是否能构成一棵树，首先要满足的就是图中只能有一个连通分量，再根据树的定义，图中不可以存在环。则由union-find算法可以很好的解决这两个问题。1.判断分量个数count是否为1；2.在构建图的构成中，如果union的两个点已经相连，则说明有环，直接return false。只需要稍微改动union方法即可：

        public boolean union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot == qRoot) return false;
            if (sz[pRoot] > sz[qRoot]) {
                id[qRoot] = pRoot;
                sz[pRoot] += sz[qRoot];
            } else {
                id[pRoot] = qRoot;
                sz[qRoot] += sz[pRoot];
            }
            count--;
            return true;
        }

### Number of Islands II
这道题需要解决的是2维矩阵上的连通性问题，且每一步都需要返回当前连通器的数量。所以用Union-Find解决问题十分合适！
虽然是2维矩阵，我们依然可以将其`扁平化`，用int[] id来记录每个位置所处连通分量，id = new int[m\*n]。  
本题的难点在于并非照搬了UF的模版，尤其count的初始化有所不同，但只要掌握了union-find的思想，就会发现依然是换汤不换药。为帮助理解，我们对以下例子给出对应的连通器变动过程:  
e.g. m = 3, n = 3, pos = [[0, 0], [0, 1], [1, 2], [2, 1], [1, 1]]
![](https://github.com/zhaoxy136/LeetCode/blob/master/Summary%20and%20Tricky%20tips/assets/Union%20Find(2).png)

    class Solution {
    int[] id;
    int[] sz;
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<>();
        int[] dir = new int[]{0, 1, 0, -1, 0};
        int count = 0;
        id = new int[m * n];
        sz = new int[m * n];
        //at the beginning, there is no any component, so we fill id with -1.
        Arrays.fill(id, -1);
        for (int[] pos : positions) {
            int index = pos[0] * n + pos[1];
            //first add the component, initialize id and sz, increase count by 1.
            id[index] = index;
            sz[index] = 1;
            count++;
            for (int i = 0; i < 4; i++) {
                int row = pos[0] + dir[i];
                int col = pos[1] + dir[i+1];
                int tmp = row * n + col;
                if (row >= 0 && row < m && col >= 0 && col < n && id[tmp] != -1) {
                    //since count is not set as global variable, we modified union method
                    count += union(index, tmp);
                }
            }
            res.add(count);
        }
        return res;
    }
    public int find(int p) {
        while (p != id[p]) {
            id[p] = id[id[p]];
            p = id[p];
        }
        return p;
    }
    public int union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return 0;
        if (sz[pRoot] > sz[qRoot]) {
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        } else {
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }
        return -1;
    }
    }


## 小结
 + union-find的题目基本都是无向图，因为它注重的是连通性，而非路径
 + union-find算法不能求具体联通路径，求路径需要用DFS搜索算法
 + quick-find 算法中，int[] id表示的是每个节点所属`分支`，可以理解为根的id;
 + quick-union算法中，int[] id表示的是每个节点的`parent节点`，所以在find()中要一直循环向上找根；
 + weighted UF算法主要引入了size数组，用于将小树向大树合并
 + compressed path十分巧妙，在find的同时优化了结构。  
 + union-find的运用要灵活，主要在于count的变化已经union的处理

## 其他相关题目
> [Number of Connected Components in an Undirected Graph](https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/description/)  
> [Number of Islands](https://leetcode.com/problems/number-of-islands/description/)

