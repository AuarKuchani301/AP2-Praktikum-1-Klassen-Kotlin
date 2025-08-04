
//li_3-5-6-7-1-2
 enum class  CourseName{
    MATH,  CHEMISTRY, ENGLISH }

class  Course(  val name : CourseName){
   var grade : Double? = null
//        set( value){
//            if ( value != null && value in 1.0..6.0)
//            field = value
//            else
//                throw IllegalArgumentException(" Die Note ist nicht valide")
//        }
      set( value) = if (value != null && value in 1.0..6.0) field = value else   throw IllegalArgumentException(" Die Note ist nicht valide")

    val passed : Boolean
        get() = if (grade != null && grade!! in 1.0..4.0) true else false

//        get(){
//            if ( grade != null && grade!! <= 4.0)
//                return true
//            else
//                return false
//        }
}


class  Student(val name: String, var age : Int){
    val courses = mutableListOf<Course>()

    fun addCourse(course: Course) = if( courses.any { it.name == course.name })  throw Exception(" invalide, schon vorhanden") else this.courses.add(course)
//       { if( courses.contains( course))
//            throw Exception(" invalide")
//        else
//        this.courses.add(course)
//    }

    fun averageGrade() = courses.sumOf { it.grade ?: 0.0 } / courses.size
//    fun averageGrade() : Double{
//        var summegrade = 0.0
//        for( course in courses )
//        {
//         summegrade += course.grade ?: 0.0
//        }
//        return summegrade / courses.size
//   }

}

class StudentClass( private val id : String){
   private  val students = mutableListOf<Student>()

    fun addStudent(student: Student) = if(students.any{it.name == student.name}) throw IllegalArgumentException("schon vorhanden") else students.add(student)
       //LiveAufgabe_3
    fun getAveragaOfStudentGread(courseName: CourseName) : Double {
        var summestudenten = 0
        var summeGerade = 0.0
        for(i in students) {
            for (g in i.courses) {
                if (g.name == courseName) {
                    summestudenten++
                    summeGerade += g.grade ?: 0.0
                }
            }
        }
        return summeGerade / summestudenten
    }

    //LiveAufgabe_5_ZähleStudenten in einer Klasse, die einen bestimmten Kurs bestanden haben.
    fun getAmountOfPassedStudentsForCourse(courseName: CourseName): Int {
        var counter = 0
        for(i in students ){
            for (g in i.courses)
            {
                if(g.name == courseName && g.passed == true)
                    counter++
            }
        }
        return counter
    }
    //auf andere Art bzw. Parameter
    fun getAmountOfPassedStudentsForCourse (kursname : Course) :Int{
        var counter = 0
        for (stud in students)
            for (kurs in stud.courses) {
                if ( kurs.name == kursname.name && kurs.passed == true)
                    counter++
            }
        return counter
    }


    //LiveAufgabe_6_ DurchschnittAltervon Studenten in einer Klasse
    fun getAverageAgeOfClass() = students.sumOf { it.age }/students.size
    // auf andere Art und weise
    fun getAverageAgeOfClass1 () : Double{
        var summAlter = 0
        for( stud in students) {
            summAlter += stud.age
        }
        return summAlter.toDouble()/ students.size
    }


    //  LiveAufgabe_7_ZähleNamenMitBuchtabe
    fun ZähleNamenMitBuchtabe(buchstabe : Char): Int {
      var counter = 0
      for (stud in students){
          if (stud.name.startsWith(buchstabe))
              counter++
           }
      return counter
    }
    //LiveAufgabe_1_
    fun getAverageGrade1() = students.sumOf { it.averageGrade() }/students.size
   //andere Lösung aber sie muss überprüft werden !!
//    fun getAverageGrade () : Double{
//        var summGrade = 0.0
//        for( i in students) {
//            for (j in i.courses)
//            summGrade += j.grade ?: 0.0
//        }
//        return summGrade/ students.size
//    }

    //LiveAufgabe_2_

        fun getBestStudentc(courseName: CourseName): Student? {
            var bestStudent: Student? = null
            var bestAverageGrade = 0.0

            for (student in students) {
                val averageGrade = student.averageGrade()
                if (student.courses.any { it.name == courseName } && averageGrade > bestAverageGrade) {
                    bestStudent = student
                    bestAverageGrade = averageGrade
                }
            }

            return bestStudent
        }




    }
fun main () {
 val mathe = Course(CourseName.MATH)
    mathe.grade = 3.0

    val math = Course (CourseName.MATH)
    val chemistry = Course (CourseName.CHEMISTRY)
    val english = Course (CourseName.ENGLISH)

    println("${mathe.grade}")
    println("${mathe.name}")
    println("${mathe.passed}")

 val student1 = Student("Shadi",20)
    println("${student1.name} ist ${student1.age}")
    val student2 = Student("Samal",28)
    println("${student1.name} ist ${student1.age}")

    student1.addCourse(mathe)
    student1.addCourse(chemistry)
  //  student1.addCourse(mathe)
    student2.addCourse(mathe)


    println("${student1.courses.size}")
    println("${student1.averageGrade()}")

  val studentClass = StudentClass("10b")
    studentClass.addStudent(student1)
    studentClass.addStudent(student2)
    //studentClass.addStudent(student1)
  println(" Der Durchschnitt des Schüleralters ist ${studentClass.getAverageAgeOfClass()}")
    println(" Zahl der Studenten, die mit den Buchstaben-S  ${studentClass.ZähleNamenMitBuchtabe('S')}")
    println(" Zahl der Studenten, die einen bestimmten Kurs bestanden Passed haben ${studentClass.getAmountOfPassedStudentsForCourse(mathe)}")
    println(" Zahl der Studenten, die einen bestimmten Kurs bestanden haben haben ${studentClass.getAmountOfPassedStudentsForCourse(CourseName.MATH)}")

//  println(studentClass.getAverageGrade())
    println(studentClass.getAverageGrade1())
    println(studentClass.getAveragaOfStudentGread(CourseName.MATH))

}

