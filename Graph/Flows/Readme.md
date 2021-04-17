# Class MaxFlow

## constructor

"java
public MaxFlow(int n)
" "

'n'Vertex'0'Create a side graph.

computational quantity

$O(n)$

## Method

### addEdge

"java
public void addEdge(int from, int to, long cap)
" "

'from' to 'to' Maximum capacity 'cap', add 0 sides and return the number of sides added.

constraint

- `0 <= from, to < n`
- `0 <= cap`

computational quantity

Narashi$O(1)$

### maxFlow

"java
// (1)
public long maxFlow(int s, int t)
// (2)
public long flow(int s, int t, long flowLimit)
" "

- (1): As long as it flows from vertex's' to 't', it returns the amount of flowing. Use the algorithm of Dinic.
(2) Flow from vertex 's' to 't' Flow As long as you can flow until you reach 'flowLimit', return the flow amount. Use the algorithm of Dinic.
- If called multiple times, the difference from the last flow is returned.

constraint

- `0 <= s, t < n`

computational quantity

'm' as the number of sides added

- (1), (2) $O(\min(n^{2/3}*m, m^{3/2}))$ (when all side capacities are one)
- (1), (2) $O(n^2*m)$

### minCut

"java
public boolean[] minCut(int s)
" "

Returns the 'boolean' array of length 'n'. The 'i'-th element is 'true' only when it is reachable from vertex 's' to 'i' in the residual graph. If maxFlow(s, t) is called just once, the return value corresponds to the mincut between 's' and 't'.

computational quantity

$O(n+m)$ as the number of sides added to 'm'

### getEdge / getEdges

The return type of 'getEdge' and 'getEdges' is 'MaxFlow.CapEdge' with the following members.

"java
class CapEdge {
    // (1)
    public final int from;
    // (2)
    public final int to;
    // (3)
    public final long cap;
    // (4)
    public final long flow;
};
" "

- (1): Returns the start point of the side. Amount to calculate: $O(1)$
- (2): Returns the end of the side. Amount to calculate: $O(1)$
- (3): Returns the capacity of a side. Amount to calculate: $O(1)$
- (4): Does not return current flow. Amount to calculate: $O(1)$

"java
// (1)
public CapEdge getEdge(int i)
// (2)
public CapEdge[] getEdges()
" "

Returns the current internal edge state. The order of the sides is the same as the order added in 'addEdge'.

constraint

- (1): The number of sides added is 'm' and '0 <= i < m'.

computational quantity

- (1): $O(1)$
- (2): $O(m)$

### changeEdge

"java
public void changeEdge(int i, long newCap, long newFlow)
" "

'i' Change the capacity and flow of the side changed to 'newCap' and 'newFlow'. The capacity and flow rate of the other side does not change.

constraint

- `0 <= newFlow <= newCap`

computational quantity

$O(1)

## Usage Example

[AtCoder Library Practice Contest D - Maxflow] (https://atcoder.jp/contests/practice2/submissions/20808482)
