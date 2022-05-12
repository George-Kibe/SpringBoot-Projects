import { StyleSheet, Dimensions } from "react-native";

const styles = StyleSheet.create({
    row:{
        flexDirection:'row',
        justifyContent:'space-between', 
        paddingVertical:20, 
        borderBottomWidth:1, 
        borderColor:"lightgrey",
        marginHorizontal:20, 
    },
    button:{
        borderWidth:1,
        width:30,
        height:30,
        borderRadius:15,
        borderColor:"#e7e7e7",
        justifyContent:'center',
        alignItems:'center'
    }
})

export default styles;