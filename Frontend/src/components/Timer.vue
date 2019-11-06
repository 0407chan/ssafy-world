<template>
  <div>
    <div v-if="this.statusType=='run'">
      <span>
        퇴근까지
      </span>
    </div>
    <div v-if="this.statusType=='run'">
      <span v-if="this.hours > 0">
        {{ hours }}시간
      </span>
      <span v-if="this.minutes > 0">
       {{ minutes }}분
      </span>
      <span>
        {{ seconds }}초
      </span>
    </div>
    <div v-if="this.statusType=='run'">
      <span>
        {{message}}
      </span>
    </div>
    <div v-if="this.statusType=='expired'">
      <div align="center">
        퇴근합시다!
      </div>
      <v-img max-width="300px" contain src="https://i.imgur.com/pkZZlTo.gif">

      </v-img>
    </div>
  </div>
</template>

<script>
import {
  mapState
} from 'vuex'

export default {
  name: 'timer',
  props: ['starttime','endtime','trans'],

  data() {
    return {

      timer:"",
      wordString: {},
      start: "",
      end: "",
      interval: "",
      minutes:"",
      hours:"",
      seconds:"",
      message:"",
      statusType:"",
      statusText: "",

      count:"",
    };
  },
  created() {
    this.wordString = JSON.parse(this.trans);
  },
  mounted() {
    this.start = new Date(this.starttime).getTime();
    this.end = new Date(this.endtime).getTime();
    // Update the count down every 1 second
    this.timerCount(this.start,this.end);
    this.interval = setInterval(() => {
      this.count++;
      if(this.count >60){
        clearInterval(this.interval);
      }
        this.timerCount(this.start,this.end);
    }, 1000)
  },
  methods:{
    timerCount(start,end){
        // Get todays date and time
        var now = new Date().getTime();

        // Find the distance between now an the count down date
        var distance = start - now;
        var passTime =  end - now;
        // console.log("distance=",distance.getMinutes()+" "+distance.getSeconds())
        // console.log("passTime=",passTime.getMinutes()+" "+passTime.getSeconds())
        if(distance < 0 && passTime < 0){
            this.message = "퇴근합시다";
            this.statusType = "expired";
            clearInterval(this.interval);
            return;

        }else if(distance < 0 && passTime > 0){
            this.calcTime(passTime);
            this.statusType = "run";
            this.message =  "남았습니다.";

        } else if( distance > 0 && passTime > 0 ){
            this.calcTime(distance);
            this.message = this.wordString.upcoming;
            this.statusType = "upcoming";
        }
    },
    calcTime(dist){
      // Time calculations for days, hours, minutes and seconds
        this.hours = Math.floor((dist % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
        this.minutes = Math.floor((dist % (1000 * 60 * 60)) / (1000 * 60));
        this.seconds = Math.floor((dist % (1000 * 60)) / 1000);
    },
  }
};
</script>
