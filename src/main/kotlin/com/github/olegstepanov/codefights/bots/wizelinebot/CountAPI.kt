package com.github.olegstepanov.codefights.bots.wizelinebot

fun countAPI(calls: MutableList<String>): MutableList<String> {
  data class Method(val name: String, var count: Int = 0)
  data class SubProject(val name: String, var count: Int = 0, val methods: MutableList<Method> = mutableListOf())
  data class Project(val name: String, var count: Int = 0, val subProjects: MutableList<SubProject> = mutableListOf())

  val projects = mutableListOf<Project>()

  fun <T> findOrAdd(list: MutableList<T>, predicate: (T) -> Boolean, value: () -> T) =
          list.find(predicate) ?: value().apply { list.add(this) }

  for (i in 0 until calls.size) {
    val call = calls[i]
    val parts = call.split('/')
    val projectName = parts[1]
    val subProjectName = parts.getOrNull(2)
    val methodName = parts.getOrNull(3)

    val project = findOrAdd(projects, { it.name == projectName }, { Project(projectName) })
    project.count++
    if (subProjectName != null) {
      val subProject = findOrAdd(project.subProjects, { it.name == subProjectName }, { SubProject(subProjectName) })
      subProject.count++
      if (methodName != null) {
        val method = findOrAdd(subProject.methods, { it.name == methodName }, { Method(methodName) })
        method.count++
      }
    }
  }

  val result = mutableListOf<String>()
  for (project in projects) {
    result.add("--${project.name} (${project.count})")
    for (subProject in project.subProjects) {
      result.add("----${subProject.name} (${subProject.count})")
      for (method in subProject.methods) {
        result.add("------${method.name} (${method.count})")
      }
    }
  }
  return result
}

fun main(args: Array<String>) {
  println(countAPI(mutableListOf("/project1/subproject1/method1",
          "/project2/subproject1/method1",
          "/project1/subproject1/method1",
          "/project1/subproject2/method1",
          "/project1/subproject1/method2",
          "/project1/subproject2/method1",
          "/project2/subproject1/method1",
          "/project1/subproject2/method1")))
}
