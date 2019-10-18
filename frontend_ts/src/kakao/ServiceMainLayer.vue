<template>
  <layer ref="layer">
    <div class="inner_delivery_layer inner_delivery_layer2" @click="$event.stopPropagation()">
      <spinner v-if="!deliveryMan"/>
      <div class="layer_body" v-else>
        <strong class="screen_out">--- 상세정보</strong>
        <profile-image :image-url="deliveryMan.imgUrl" additionClass="box_courier_profile"/>
        <!-- 2019-02-11 수정 -->
        <!-- 2019-01-21 수정 -->
        <dl class="list_courier_info">
          <dt class="screen_out">--명</dt>
          <dd class="txt_center">{{deliveryMan.storeName}}</dd>
          <dt class="screen_out">---- 이름</dt>
          <dd class="txt_name">{{deliveryMan.name}} --님</dd>
          <fragment v-if="deliveryMan.phoneNum">
            <dt class="screen_out">---- 전화번호</dt>
            <dd class="txt_phone">
              <a :href="`tel://${deliveryMan.phoneNum}`" class="link_call">{{deliveryMan.phoneNum}}</a>
            </dd>
          </fragment>
        </dl>
      </div>
      <div class="layer_foot">
        <custom-button class="btn_layer btn_confirm" @click="layer.hide()">확인</custom-button>
      </div>
    </div>
  </layer>
</template>
<script lang="ts">
import { Vue, Component, Prop } from 'vue-property-decorator';
import Layer from '~/components/layout/Layer.vue';
import ProfileImage from '~/components/delivery/ProfileImage.vue';
import { IDeliveryManInfo } from '~/interfaces/delivery';
import api from '~/apis/delivery';
import { formatPhoneNumber } from '~/helpers/phoneNumber';

@Component({
  components: { Layer, ProfileImage },
})
export default class DeliveryManLayer extends Vue {
  $refs!: {
    layer: Layer;
  };

  @Prop()
  orderId?: string;

  deliveryMan: IDeliveryManInfo | null = null;

  get layer() {
    return this.$refs.layer;
  }

  public beforeMount() {
    this.fetchDeliveryManInfo();
  }

  public show() {
    this.layer.show();
  }

  private formatPhoneNumber(phoneNum: string) {
    return formatPhoneNumber(phoneNum);
  }

  private async fetchDeliveryManInfo() {
    this.deliveryMan = await api.fetchDeliveryManInfo(this.orderId!);
  }
}
</script>
