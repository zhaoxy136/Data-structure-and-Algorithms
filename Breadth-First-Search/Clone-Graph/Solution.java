//Version 0:
/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;
        List<UndirectedGraphNode> vertices = new ArrayList<>();
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        vertices.add(node);
        map.put(node, new UndirectedGraphNode(node.label));
        int start = 0;
        //clone vertices
        while (start < vertices.size()) {
            List<UndirectedGraphNode> neighbors = vertices.get(start++).neighbors;
            for (UndirectedGraphNode neighbor : neighbors) {
                if (!map.containsKey(neighbor)) {
                    vertices.add(neighbor);
                    map.put(neighbor, new UndirectedGraphNode(neighbor.label));
                }
            }
        }
        //clone edges
        for (UndirectedGraphNode vertex : vertices) {
            for (UndirectedGraphNode neighbor : vertex.neighbors) {
                map.get(vertex).neighbors.add(map.get(neighbor));
            }
        }
        return map.get(node);
    }
}
