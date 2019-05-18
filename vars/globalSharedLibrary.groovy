import groovy.transform.Field
import java.text.SimpleDateFormat

@Field acceptType = 200

@groovy.transform.Field common = [
	currentDate: "${new SimpleDateFormat("MM.dd.yyyy-HH.mm.ss").format(new Date())}",
	currentYear: "${new SimpleDateFormat("yyyy").format(new Date())}",
	currentMonth: "${new SimpleDateFormat("MM").format(new Date())}",
	currentDay: "${new SimpleDateFormat("dd").format(new Date())}",
	currentTime: "${new SimpleDateFormat("HH.mm.ss").format(new Date())}",
	currentHour: "${new SimpleDateFormat("HH").format(new Date())}"
]

@groovy.transform.Field synonyms = [
	mvn: ["mvn", "maven", "mavens", "java"]
]