#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

int main() {
	int childpid;
	int status = 1;
	int zakonczono;
	printf("startuje proces macierzysty pid %d\n", getpid());
	if ((childpid = fork()) == -1) {
		perror("nie moge forknac");
		exit(1);
	}
	else {
		if(childpid == 0) {
			printf("Proces potomny o pidzie %d z rodzica %d\n", getpid(), getppid());
			sleep(3);
			exit(2);
		}
		else {
			printf("Proces macierzysty o pidzie %d i dziecku %d\n", getpid(), childpid);
			wait(&status);
			printf("Wynik potomka: %d\n", status);
		}
	}
exit(0);
}
