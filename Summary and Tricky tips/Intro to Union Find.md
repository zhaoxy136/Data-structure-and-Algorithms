# Introduction to Union Find(并查集）

## 涉及题目
 > [Friend Circles](https://leetcode.com/problems/friend-circles/description/)  
 > []()  
 > []()  
 
 
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

则我们的初步实现为：

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

union操作的复杂度过高，于是我们引入树的概念，当做union时，即为将两个树合并的过程：先找到两个节点所在树的根节点，然后合并。

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






### Friend Circles








## 小结
 + quick-find 算法中，int[] id表示的是每个节点所属`分支`，可以理解为根的id;
 + quick-union算法中，int[] id表示的是

## 其他相关题目


