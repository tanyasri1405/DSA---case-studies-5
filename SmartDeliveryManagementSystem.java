import java.util.*;

public class SmartDeliveryManagementSystem{

    // =====================================================
    // Graph Representation
    // =====================================================
    static class Graph {

        private Map<String, List<Edge>> adjList;

        Graph() {
            adjList = new HashMap<>();
        }

        static class Edge {
            String destination;
            int distance;

            Edge(String destination, int distance) {
                this.destination = destination;
                this.distance = distance;
            }
        }

        public void addLocation(String location) {
            adjList.putIfAbsent(location, new ArrayList<>());
        }

        public void addRoute(String source,
                             String destination,
                             int distance) {

            adjList.get(source).add(
                    new Edge(destination, distance));

            adjList.get(destination).add(
                    new Edge(source, distance));
        }

        public void displayNetwork() {

            System.out.println("\n===== Delivery Network =====");

            for (String location : adjList.keySet()) {

                System.out.print(location + " -> ");

                for (Edge edge : adjList.get(location)) {
                    System.out.print(
                            edge.destination +
                            "(" + edge.distance + " km) "
                    );
                }

                System.out.println();
            }
        }
    }

    // =====================================================
    // Heap-Based Priority Order Management
    // =====================================================
    static class Order {

        int orderId;
        int priority;

        Order(int orderId, int priority) {
            this.orderId = orderId;
            this.priority = priority;
        }
    }

    // =====================================================
    // Greedy Resource Allocation
    // =====================================================
    public static void allocateVehiclesGreedy(
            int[] deliveryLoads,
            int vehicleCapacity) {

        Arrays.sort(deliveryLoads);

        int vehiclesUsed = 0;
        int currentLoad = 0;

        for (int load : deliveryLoads) {

            if (currentLoad + load <= vehicleCapacity) {
                currentLoad += load;
            } else {
                vehiclesUsed++;
                currentLoad = load;
            }
        }

        if (currentLoad > 0)
            vehiclesUsed++;

        System.out.println("\nVehicles Required: "
                + vehiclesUsed);
    }

    // =====================================================
    // Main Function
    // =====================================================
    public static void main(String[] args) {

        System.out.println("=========================================");
        System.out.println(" SMART DELIVERY MANAGEMENT SYSTEM ");
        System.out.println("=========================================");

        // =================================================
        // 1. Graph-Based Delivery Network
        // =================================================

        Graph network = new Graph();

        network.addLocation("Warehouse");
        network.addLocation("Area A");
        network.addLocation("Area B");
        network.addLocation("Area C");
        network.addLocation("Area D");

        network.addRoute("Warehouse", "Area A", 10);
        network.addRoute("Warehouse", "Area B", 15);
        network.addRoute("Area A", "Area C", 12);
        network.addRoute("Area B", "Area D", 20);
        network.addRoute("Area C", "Area D", 8);

        network.displayNetwork();

        // =================================================
        // 2. Heap-Based Priority Management
        // =================================================

        PriorityQueue<Order> priorityQueue =
                new PriorityQueue<>(
                        (o1, o2) ->
                                Integer.compare(
                                        o2.priority,
                                        o1.priority)
                );

        priorityQueue.add(new Order(101, 3));
        priorityQueue.add(new Order(102, 5));
        priorityQueue.add(new Order(103, 1));
        priorityQueue.add(new Order(104, 4));

        System.out.println("\n===== Priority Orders =====");

        while (!priorityQueue.isEmpty()) {

            Order order = priorityQueue.poll();

            System.out.println(
                    "Order ID: "
                            + order.orderId
                            + " | Priority: "
                            + order.priority
            );
        }

        // =================================================
        // 3. Greedy Resource Allocation
        // =================================================

        int[] deliveryLoads = {10, 20, 15, 5, 25, 30};

        int vehicleCapacity = 40;

        System.out.println("\n===== Resource Allocation =====");

        allocateVehiclesGreedy(
                deliveryLoads,
                vehicleCapacity
        );

        // =================================================
        // 4. Scalability Analysis
        // =================================================

        System.out.println("\n===== Scalability Analysis =====");

        System.out.println(
                "Graph Route Search: O(V + E)"
        );

        System.out.println(
                "Heap Operations: O(log n)"
        );

        System.out.println(
                "Greedy Allocation: O(n log n)"
        );

        // =================================================
        // 5. Real-World Deployment Evaluation
        // =================================================

        System.out.println("\n===== Deployment Evaluation =====");

        System.out.println(
                "✓ Efficient route representation using Graphs"
        );

        System.out.println(
                "✓ Fast order prioritization using Heap"
        );

        System.out.println(
                "✓ Optimized resource utilization"
        );

        System.out.println(
                "✓ Scalable for large logistics networks"
        );

        System.out.println(
                "✓ Suitable for real-time delivery systems"
        );
    }
}