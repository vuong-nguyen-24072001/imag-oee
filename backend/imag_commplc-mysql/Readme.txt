khi deploy, cần phải chỉnh code để phù hợp với docker:
	- vào application.properties: thay đổi thông tin mysql được pull và setup trong docker
	(nên setup password và username là root khi build image mysql)
	- vào dataAPI: thay đổi ở API resetData theo comment
	- vào database.java trong Utils: thay đổi theo comment