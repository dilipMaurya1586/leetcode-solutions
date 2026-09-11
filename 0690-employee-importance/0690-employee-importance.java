class Solution {
    public int getImportance(List<Employee> employees, int id) {

        for(Employee employee : employees) {
            if(employee.id == id) {
                return dfs(employees, employee);
            }
        }
        return 0;
    }

    private int dfs(List<Employee> employees, Employee employee) {

        int total = employee.importance;

        for (int subordinatedId : employee.subordinates) {

            for (Employee subordinate : employees) {

                if (subordinate.id == subordinatedId) {

                    total += dfs(employees, subordinate);
                    break;
                }
            }
        }
        return total;
    }
}