import { View, Button } from '@tarojs/components'
import { useLoad, navigateTo } from '@tarojs/taro'
import './index.css'

export default function Index () {
  useLoad(() => {
    console.log('Page loaded.')
  })

  function handleSubmit(){
navigateTo({
  url: "pages/product/index"
});
  }
  return (
    <View className='container'>
      <Button onClick={() => handleSubmit()} size={'mini'} >点击进入产品页</Button>
    </View>
  )
}