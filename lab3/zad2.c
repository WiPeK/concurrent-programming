#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>

char childBufor[100], parentBufor[100], fileName[100];
int childPipe[2], parentPipe[2];
int childpid;

void openPipe(int pipeDescriptor[2]) {
    if(pipe(pipeDescriptor)) {
		perror("Pipe error\n");
    }
}

void blockPipe(int childValue, int parentValue) {
    close(childPipe[childValue]);
    close(parentPipe[parentValue]);
}

int main(){
    openPipe(childPipe);
    openPipe(parentPipe);

    if((childpid = fork()) == -1){
   	 perror("Blad fork\n");
    } else {
   	 if(childpid == 0){
   		 blockPipe(1,0);

   		 read(childPipe[0], childBufor, sizeof(childBufor));
		 printf("Client file name: %s\n", childBufor);
   		 FILE * file = fopen(childBufor, "r");

   		 if(file != NULL){
   			 while(fgets(childBufor, sizeof(childBufor), file) != NULL) {
   				 write(parentPipe[1], childBufor, sizeof(childBufor));
   			 }
   		 } else {    
   			 write(parentPipe[1], "Can not open file\n", 21);
   		 }

   		 fclose(file);
   		 blockPipe(0,1);
   	 } else {
   		 blockPipe(0,1);
   		 printf("Type file name: ");   	 
   		 scanf("%s", fileName);
		 printf("File name: %s\n", fileName);
   		 write(childPipe[1], fileName, strlen(fileName)+1);

   		 while(read(parentPipe[0], parentBufor, sizeof(parentBufor)) > 0) {
               			 write(1, parentBufor, strlen(parentBufor)+1);
   		 }

   		 wait(NULL);
   		 blockPipe(1,0);
   	 }
    }

    return 0;
}