import groovy.transform.Field
import java.text.SimpleDateFormat

@Field acceptType = 200

@groovy.transform.Field common = [
	currentDate: "${new SimpleDateFormat("MM.dd.yyyy-HH.mm.ss").format(new Date())}",
	currentYear: "",
	currentMonth: "",
	currentDay: "",
	currentTime: "",
	currentHour: ""
]