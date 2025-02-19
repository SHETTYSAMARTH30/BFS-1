//Time complexity: O(V + E)
//Space complexity: O(V + E)
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        
        //We will perform topological sort
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        //Keeps track of incoming edges to a vertex
        int[] indegree = new int[numCourses];

        for(int i = 0; i < prerequisites.length; i++) {

            int dep = prerequisites[i][0];
            int indep = prerequisites[i][1];

            //if the key is not present we first create the list
            if(!adjList.containsKey(indep)) {

                adjList.put(indep, new ArrayList<>());
            }

            adjList.get(indep).add(dep);
            //Keep track of incoming edges
            indegree[dep]++;
        }

        //Get all the courses with indegree 0, because there are independent and can be completed first
        Queue<Integer> q = new LinkedList<>();
        int count = 0;
        for(int i = 0; i < numCourses; i++) {
            
            if(indegree[i] == 0) {
                q.add(i);
                count++;
            }
        }

        //if q is empty that means no independent courses so return false
        if(q.isEmpty())
            return false;

        //If q contains all the courses then return true we don't need to check further as all courses are independent
        if(count == numCourses)
            return true;

        //Start the breadth first search
        //Remove the edges one by one and check if there is any independent course which can be completed
        //Keep track of all courses in order
        //List<Integer> result = new ArrayList<>();

        while(!q.isEmpty()) {

            int course = q.poll();
            //result.add(course);

            //here we don't need distinction/levels hence we don't use for loop
            List<Integer> children = adjList.get(course);

            //Remove edges from all the neighbors
            if(children == null)
                continue;

            for(int child: children) {

                //Remove a edge from neighbors
                indegree[child]--;
                
                //if they course is no longer dependent add to queue
                if(indegree[child] == 0) {
                    q.add(child);
                    count++;
                }
            }
        }

        //if all the courses are completed
        return count == numCourses;
    }
}