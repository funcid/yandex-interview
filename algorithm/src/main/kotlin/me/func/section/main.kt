package me.func.section

fun main() {
    val connections = listOf(
        "A" to "B",
        "B" to "C",
        "C" to "D"
    )
    findValidPath(connections)
}

fun findValidPath(connections: List<Pair<String, String>>) {
    // Input: [(A, B), (B, C), (C, D)]
    val graph = HashMap<String, MutableList<String>>()

    // После трансформации
    // A: B
    // B: A, C
    // C: B, D
    // D: C
    connections.forEach { (from, to) ->
        graph.getOrPut(from) { mutableListOf() }.add(to)
        graph.getOrPut(to) { mutableListOf() }.add(from)
    }

    // Ищем первый уникальный город
    val start = graph.entries.firstOrNull { it.value.size == 1 }?.key ?: throw RuntimeException("No path available")

    val visited = mutableSetOf<String>()
    val output = mutableListOf<String>()
    val stack = ArrayDeque<String>()

    stack.addFirst(start)

    while (stack.isNotEmpty()) {
        val current = stack.removeFirst()

        if (!visited.add(current)) continue

        output.add(current)
        graph[current]
            ?.filter { !visited.contains(it) }
            ?.forEach { stack.addFirst(it) }
    }

    if (output.size != graph.size)
        throw RuntimeException("No valid path covering all cities")

    println("Valid path: ${output.joinToString(" -> ")}")
}
