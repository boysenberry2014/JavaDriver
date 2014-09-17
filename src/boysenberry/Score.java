package boysenberry;

import java.io.File;

public class Score {
	int score_moment;
	File file = new File(/srs/Scoreboard.txt);
	if(file.exists()) {}
		void Score(){
			score_moment=0;
		}
		
		void enemy_kill_type1(){			score_moment+=50;		}
		
		void enemy_kill_type2(){			score_moment+=250;		}
		
		void enemy_kill_type3(){			score_moment+=1000;		}
		
		void missed_projektile(){			score_moment-=1;		}
		
		void asteroid_destroyed_small(){			score_moment+=50;		}
		
		void asteroid_destroyed_big(){			score_moment+=25;		}
		
		void showlogfile(){	}
		
		void add_scoreto_log(){}
}
