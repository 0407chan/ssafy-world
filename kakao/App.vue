<template>
  <div id="kWrap" :class="wrapClass">
    <header>
      <div class="k_head"><h1 class="screen_out">sample</h1></div>
    </header>
    <hr class="hide" />
    <router-view />
  </div>
</template>
<script lang="ts">
import { Vue, Component, Watch } from 'vue-property-decorator';
import { ORIGIN_URL, ROUTE } from '~/router';
import { trackPage, trackEvent, TIARA_TRACK_EVENT, isTitlebarLoggingTargetPage } from '~/helpers/tiaraLogger';
import { IReferrer } from '~/interfaces/tiara';

@Component
export default class App extends Vue {
  get wrapClass() {
    return this.$route.meta.wrapClass || '';
  }

  @Watch('$route.meta')
  onChangeRouteMata() {
    this.trackTiaraPage();
  }

  public created() {
    WAVE.TitleBar.initDefaultRightEvent(() => {
      const result = confirm('-- 서비스를 종료하시겠습니까?');
      if (result) {
        const { page } = this.$route.meta;
        if (page && isTitlebarLoggingTargetPage(page)) {
          trackEvent(page, TIARA_TRACK_EVENT.CLICK_CLOSE);
        }
      }
    });

    this.trackTiaraPage();
    this.preventDuplicateHistory();
  }

  private backHandler(backEvent: () => void) {
    const { page } = this.$route.meta;
    if (page && isTitlebarLoggingTargetPage(page)) {
      trackEvent(page, TIARA_TRACK_EVENT.CLICK_BACK);
    }
    backEvent();
  }

  private preventDuplicateHistory() {
    const filterRoutes = [
      ROUTE.CLOSE.name,
      ROUTE.JOIN.name,
      ROUTE.DELIVERY_APPLY_COMPLETE_CVS.name,
      ROUTE.DELIVERY_APPLY_COMPLETE_VISIT.name,
    ];

    if (window.document.referrer && !window.document.referrer.match(ORIGIN_URL)) {
      if (filterRoutes.indexOf(this.$route.name as string) === -1) {
        window.history.back();
      }
    }
  }

  private trackTiaraPage() {
    const baseReferrer: IReferrer = JSON.parse(sessionStorage.getItem('t_referrer')!);

    const { t_src: tSrc, t_ch: tCh, t_obj: tObj } = this.$route.query;

    if ([tSrc, tCh, tObj].every(this.validateTiaraReferrer)) {
      sessionStorage.setItem(
        't_referrer',
        JSON.stringify({
          t_src: tSrc ? tSrc : baseReferrer && baseReferrer.t_src ? baseReferrer.t_src : '',
          t_ch: tCh ? tCh : baseReferrer && baseReferrer.t_ch ? baseReferrer.t_ch : '',
          t_obj: tObj ? tObj : baseReferrer && baseReferrer.t_obj ? baseReferrer.t_obj : '',
        })
      );
    }

    const { page, title } = this.$route.meta;

    if (page) {
      trackPage(page, title);
    }
  }

  private validateTiaraReferrer(value?: string | string[]) {
    if (!value) {
      return true;
    } else if (typeof value !== 'string') {
      return false;
    }

    return /^[a-zA-Z0-9?&=_-]+$/.test(value);
  }
}
</script>