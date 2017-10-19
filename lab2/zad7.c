#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include<signal.h>

void obsluz_sygnal(int sig_nr) {
	printf("Koniec procesu potomka, nr sygnalu %d\n", sig_nr);
}


int main() {
	signal(SIGCHLD, obsluz_sygnal);
	int childpid;
	int status = 1;
	int zakonczono;
	int i;
	for (i = 0; i < 5; i++) {

	if ((childpid = fork()) == -1) {
		perror("nie moge forknac");
		exit(1);
	}
	else if(childpid == 0) {
			printf("Proces potomny o pidzie %d z rodzica %d\n", getpid(), getppid());
			exit(1);
		}
	else {
sleep(10);
			printf("Proces macierzysty o pidzie %d i dziecku %d\n", getpid(), childpid);
		}
	
	}
exit(0);
}
